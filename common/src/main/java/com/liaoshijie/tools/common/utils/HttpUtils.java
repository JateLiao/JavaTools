package com.liaoshijie.tools.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/3/21 上午10:49
 */
@Slf4j
public class HttpUtils {

    public static final Map<String, String> HEADER_DEFAULT = Collections.emptyMap();

//    private static CloseableHttpClient httpClient;
//
//    static {
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        cm.setMaxTotal(100);
//        cm.setDefaultMaxPerRoute(50);
//        httpClient = HttpClients.custom()
//                .setConnectionManager(cm)
//                //设置共享连接池
//                .setConnectionManagerShared(true)
//                .build();
//    }

    public static void init() {
    }


    /**
     * http get
     *
     * @param url   可带参数的 url 链接
     * @param heads http 头信息
     */
    public static String get(String url, Map<String, String> heads) {
        CloseableHttpResponse httpResponse = null;
        String result = "";
        HttpGet httpGet = new HttpGet(url);
        if (heads != null) {
            Set<String> keySet = heads.keySet();
            for (String s : keySet) {
                httpGet.addHeader(s, heads.get(s));
            }
        }

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, "utf-8");
            }

        } catch (IOException e) {
            log.error("http get request error ", e);
        } finally {
            try {
                if (Objects.nonNull(httpResponse)) {
                    httpResponse.getEntity().getContent().close();
                    httpResponse.close();
                }
            } catch (IOException ex) {
                log.error("close httpResponse error");
            }
        }
        return result;
    }

    /**
     * post 方法
     *
     * @param url   post 的 url
     * @param data  数据 application/json 的时候 为json格式
     * @param heads Http Head 参数
     */
    public static String post(String url, String data, Map<String, String> heads) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (heads != null) {
            Set<String> keySet = heads.keySet();
            for (String s : keySet) {
                httpPost.addHeader(s, heads.get(s));
            }
        }
        StringEntity stringEntity = new StringEntity(data, Charset.forName("UTF-8"));
        httpPost.setEntity(stringEntity);

        // Create a custom response handler
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            return httpClient.execute(httpPost, responseHandler);
        }
    }

    /**
     * 微信部分请求接口专用
     *
     * @param url
     * @param requestJson
     * @param heads
     * @return
     */
    public static HttpEntity getPostEntity(String url, String requestJson, Map<String, String> heads) {
        HttpPost httpRequest = new HttpPost(url);
        if (heads != null) {
            Set<String> keySet = heads.keySet();
            for (String s : keySet) {
                httpRequest.addHeader(s, heads.get(s));
            }
        }
        CloseableHttpResponse httpResponse = null;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            StringEntity se = new StringEntity(requestJson, "utf-8");
            httpRequest.setEntity(se);
            httpResponse = httpClient.execute(httpRequest);
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                return httpResponse.getEntity();
            }
        } catch (Exception e) {
            log.error("http post request error ", e);
        } finally {
            if (Objects.nonNull(httpResponse)) {
                try {
                    httpResponse.getEntity().getContent().close();
                    httpResponse.close();
                } catch (IOException e) {
                    log.error("close httpResponse error");
                }
            }
        }
        return null;
    }

    public static String post(String url, Map<String, String> urlParam, Object json) throws IOException {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        StringBuilder result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            if (Objects.nonNull(urlParam) && !CollectionUtils.sizeIsEmpty(urlParam)) {
                url = url + "?" + parseParam(urlParam);
            }
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            if (Objects.nonNull(json)) {
                StringEntity entity = new StringEntity(GsonUtil.toJson(json));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            result = new StringBuilder();
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                result.append(line).append(NL);
            }
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
            if (Objects.nonNull(response)) {
                response.close();
            }
        }
        return result.toString();
    }

    /**
     * put 方法
     *
     * @param url   put 的 url
     * @param data  数据 application/json 的时候 为json格式
     * @param heads Http Head 参数
     */
    public static String put(String url, String data, Map<String, String> heads) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        if (heads != null) {
            Set<String> keySet = heads.keySet();
            for (String s : keySet) {
                httpPut.addHeader(s, heads.get(s));
            }
        }
        StringEntity stringEntity = new StringEntity(data);
        httpPut.setEntity(stringEntity);

        // Create a custom response handler
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            return httpClient.execute(httpPut, responseHandler);
        }
    }

    /**
     * delete 方法
     *
     * @param url   delete 的 url
     * @param heads Http Head 参数
     */
    public static String delete(String url, Map<String, String> heads) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        if (heads != null) {
            Set<String> keySet = heads.keySet();
            for (String s : keySet) {
                httpDelete.addHeader(s, heads.get(s));
            }
        }

        // Create a custom response handler
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            return httpClient.execute(httpDelete, responseHandler);
        }
    }

    /**
     * delete 方法
     *
     * @param url   patch 的 url
     * @param heads Http Head 参数
     */
    public static String patch(String url, String data, Map<String, String> heads) throws IOException {
        HttpPatch httpPatch = new HttpPatch(url);
        if (heads != null) {
            Set<String> keySet = heads.keySet();
            for (String s : keySet) {
                httpPatch.addHeader(s, heads.get(s));
            }
        }
        StringEntity stringEntity = new StringEntity(data);
        httpPatch.setEntity(stringEntity);

        // Create a custom response handler
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            return httpClient.execute(httpPatch, responseHandler);
        }
    }

    /**
     * @param map
     * @return param 是 name1=value1&name2=value2 的形式
     */
    private static String parseParam(Map<String, String> map) throws UnsupportedEncodingException {
        // 编码请求参数
        if (Objects.isNull(map) || CollectionUtils.sizeIsEmpty(map)) {
            return "";
        }
        return map.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }
}
