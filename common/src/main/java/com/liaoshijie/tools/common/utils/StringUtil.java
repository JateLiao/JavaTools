/*
 * 文件名：StringUtil.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： StringUtil.java
 * 修改人：tashan
 * 修改时间：2015年4月9日
 * 修改内容：新增
 */
package com.liaoshijie.tools.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p> 
 * TODO 示例代码
 *
 * <pre>
 * </pre>
 *
 * @author tashan
 */
public final class StringUtil {
    /**
     * 构造函数.
     *
     */
    private StringUtil() {
        throw new RuntimeException("this is a util class,can not instance");
    }

    /**
     * TODO 添加方法注释.
     *
     * @param str
     *            字符串
     * @return String 转换后字符串
     */
    public static String removeCharacter(String str) {
        if (null == str || str.length() == 0) {
            return str;
        }
        str = "ss" + str;
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (('@' == chars[i] && chars[i - 1] == '"' && chars[i - 2] == '{') || ('@' == chars[i] && chars[i - 1] == '"' && chars[i - 2] == ',')) {
                continue;
            }
            sb.append(chars[i]);
        }
        String result = sb.substring(2);
        result = result.replaceAll("\\{\"ID\"", "\\{\"id\"");
        result = result.replaceAll(",\"ID\"", ",\"id\"");
        result = result.replaceAll("\\{\"URL\"", "\\{\"url\"");
        result = result.replaceAll(",\"URL\"", ",\"url\"");
        result = result.replaceAll("\"Defalut\":", ",\"DefalutXXX\":");
        return result;
    }

    /**
     *
     * 判断字符串作为参数是否合法.
     *
     * @param str
     *            指定字符串
     * @return null或空字符串"" 返回true
     */
    public static boolean isStringParamNotLegal(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     *
     * 根据传入对象返回指定格式的字符串.
     *
     * @param obj
     *            参数对象
     * @return 返回以这种格式的字符串" (fieldName:value;)* "字符串最后的";"没有
     * @throws Exception
     *             传入参数对象最好应该是model，对应的属性有公用的get方法。
     */
    public static String getObjectString(Object obj) throws Exception {
        StringBuilder strBuilder = new StringBuilder();
        if (obj == null) {
            return "";
        }
        Class<? extends Object> type = obj.getClass();
        if (String.class.equals(type) || char.class.equals(type) || Integer.class.equals(type) || int.class.equals(type) || Double.class.equals(type) || double.class.equals(type)) {
            return "";
        } else if (Long.class.equals(type) || long.class.equals(type) || Float.class.equals(type) || float.class.equals(type)) {
            return "";
        } else if (Boolean.class.equals(type) || boolean.class.equals(type) || Byte.class.equals(type) || byte.class.equals(type) || Short.class.equals(type) || short.class.equals(type)) {
            return "";
        } else {
            Field[] fields = type.getDeclaredFields();
            String fieldName;
            Class<? extends Object> fieldType;
            String methodName;
            for (Field field : fields) {
                fieldName = field.getName();
                fieldType = field.getType();
                if (String.class.equals(fieldType) || char.class.equals(fieldType) || Integer.class.equals(fieldType) || int.class.equals(fieldType) || Double.class.equals(fieldType)
                        || float.class.equals(fieldType)) {
                    methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = type.getMethod(methodName);
                    Object fieldValue = method.invoke(obj);
                    if (fieldValue != null && !"".equals(fieldValue)) {
                        String fieldNameNew = fieldName.replaceAll("XXX", ".");
                        strBuilder.append(fieldNameNew);
                        strBuilder.append(":");
                        strBuilder.append(fieldValue);
                        strBuilder.append(";");
                    }
                } else if (Short.class.equals(fieldType) || short.class.equals(fieldType) || Long.class.equals(fieldType) || long.class.equals(fieldType) || Float.class.equals(fieldType)) {
                    methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = type.getMethod(methodName);
                    Object fieldValue = method.invoke(obj);
                    if (fieldValue != null && !"".equals(fieldValue)) {
                        String fieldNameNew = fieldName.replaceAll("XXX", ".");
                        strBuilder.append(fieldNameNew);
                        strBuilder.append(":");
                        strBuilder.append(fieldValue);
                        strBuilder.append(";");
                    }
                } else if (double.class.equals(fieldType) || Boolean.class.equals(fieldType) || boolean.class.equals(fieldType) || Byte.class.equals(fieldType) || byte.class.equals(fieldType)) {
                    methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = type.getMethod(methodName);
                    Object fieldValue = method.invoke(obj);
                    if (fieldValue != null && !"".equals(fieldValue)) {
                        String fieldNameNew = fieldName.replaceAll("XXX", ".");
                        strBuilder.append(fieldNameNew);
                        strBuilder.append(":");
                        strBuilder.append(fieldValue);
                        strBuilder.append(";");
                    }

                }
            }
        }
        if (0 == strBuilder.length()) {
            return "";
        }
        return strBuilder.toString().substring(0, strBuilder.toString().length() - 1);
    }

    /**
     * 大写转小写.
     *
     * @param reConvertJson
     *            json
     * @return String
     */
    public static String upperConvertToLower(String reConvertJson) {
        char[] chars = reConvertJson.toCharArray();
        StringBuffer resultString = new StringBuffer();
        int check = 0;
        for (char var : chars) {
            if (var == '{' || var == ',') {
                check = 1;
            } else {
                if (check == 1) {
                    if (var == '"') {
                        check = 2;
                    } else {
                        check = 0;
                    }
                } else if (check == 2) {
                    if (Character.isUpperCase(var)) {
                        var = Character.toLowerCase(var);
                    }
                    check = 0;
                }
            }
            resultString.append(var);
        }
        return resultString.toString();

    }

    /**
     * 小写转大写.
     *
     * @param reConvertJson
     *            json
     * @return String
     */
    public static String lowerConvertToUpper(String reConvertJson) {
        char[] chars = reConvertJson.toCharArray();
        StringBuffer resultString = new StringBuffer();
        int check = 0;
        for (char var : chars) {
            if (var == '{' || var == ',') {
                check = 1;
            } else {
                if (check == 1) {
                    if (var == '"') {
                        check = 2;
                    } else {
                        check = 0;
                    }
                } else if (check == 2) {
                    if (Character.isLowerCase(var)) {
                        var = Character.toUpperCase(var);
                    }
                    check = 0;
                }
            }
            resultString.append(var);
        }
        return resultString.toString();

    }

    /**
     * 去掉空值 "".
     *
     * @param reConvertJson
     *            e
     * @return String
     */
    public static String removeEmpty(String reConvertJson) {
        // Pattern pattern = Pattern.compile("\"[a-z|A-Z|0-9]*\":\"\"");
        // Matcher matcher = pattern.matcher(reConvertJson);
        // String temp = matcher.replaceAll("");
        // temp = temp.replaceAll("\\{,", "\\{");
        // temp = temp.replaceAll(",\\}", "\\}");
        // temp.replaceAll(",,", ",");

        Pattern pattern = Pattern.compile("\"[a-z|A-Z|0-9]*\":\"\"");
        Matcher matcher = pattern.matcher(reConvertJson);
        String temp = matcher.replaceAll("");
        temp = temp.replace("\"\"", "");
        Pattern pattern1 = Pattern.compile("[,]+");
        Matcher matcher1 = pattern1.matcher(temp);
        temp = matcher1.replaceAll(",");
        temp = temp.replaceAll("\\{,", "\\{");
        temp = temp.replaceAll(",\\}", "\\}");
        temp = temp.replaceAll("\\[,", "\\[");
        temp = temp.replaceAll(",\\]", "\\]");
        return temp;

    }

    /**
     * 添加[].
     *
     * @param reConvertJson
     *            json
     * @param keyString
     *            需要变成list的字段
     * @return String
     */
    public static String buildList(String reConvertJson, String keyString) {
        keyString = "\"" + keyString + "\":";
        int index = reConvertJson.indexOf(keyString);
        if (index == -1) {
            return reConvertJson;
        }
        String head = reConvertJson.substring(0, index);
        String tail = reConvertJson.substring(index + keyString.length(), reConvertJson.length());
        char[] chars = tail.toCharArray();
        StringBuffer resultString = new StringBuffer(head + keyString);
        boolean isFirstCahar = true;
        int frontbracket = 0;
        for (char var : chars) {
            if (isFirstCahar) {
                if (var == '[') {
                    return reConvertJson;
                } else if (var == '{') {
                    isFirstCahar = false;
                    resultString.append("[");
                    resultString.append(var);
                    frontbracket++;
                }
            } else {
                if (var == '{') {
                    frontbracket++;
                } else if (var == '}') {
                    frontbracket--;
                }
                resultString.append(var);
                if (frontbracket == 0) {
                    resultString.append("]");
                    break;
                }
            }

        }
        resultString.append(reConvertJson.substring(resultString.toString().length() - 2));
        return resultString.toString();
    }

    /**
     * 在xml的标签中，小写，转大写.
     *
     * @param xmlStr
     *            xmlStr
     * @return String
     */
    public static String lowerToUpperForXml(String xmlStr) {
        char[] chars = xmlStr.toCharArray();
        StringBuffer resultString = new StringBuffer();
        boolean isLabel = false;
        for (char var : chars) {
            if (var == '<') {
                isLabel = true;
            } else {

                if (var == '/') {
                    isLabel = true;
                } else if (isLabel && Character.isLowerCase(var)) {
                    var = Character.toUpperCase(var);
                    isLabel = false;
                }
            }
            resultString.append(var);
        }
        return resultString.toString();
    }

    /**
     * 在xml的标签中，小写，转大写.
     *
     * @param xmlStr
     *            xmlStr
     * @return String
     */
    public static String upperToLowerForXml(String xmlStr) {
        char[] chars = xmlStr.toCharArray();
        StringBuffer resultString = new StringBuffer();
        boolean isLabel = false;
        for (char var : chars) {
            if (var == '<') {
                isLabel = true;
            } else {

                if (var == '/') {
                    isLabel = true;
                } else if (isLabel && Character.isUpperCase(var)) {
                    var = Character.toLowerCase(var);
                    isLabel = false;
                }
            }
            resultString.append(var);
        }
        return resultString.toString();
    }

    /**
     * 将指定字符key的属性值，转为数组. key：value=》key：[value]
     *
     * @param reConvertJson
     *            d
     * @param keyString
     *            d
     * @return String
     */
    public static String stringToListForJson(String reConvertJson, String keyString) {
        keyString = "\"" + keyString + "\":";
        int index = reConvertJson.indexOf(keyString);
        if (index == -1) {
            return reConvertJson;
        }
        String head = reConvertJson.substring(0, index);
        String tail = reConvertJson.substring(index + keyString.length(), reConvertJson.length());
        char[] chars = tail.toCharArray();
        StringBuffer resultString = new StringBuffer(head + keyString);
        boolean isFirstCahar = true;
        for (char var : chars) {
            if (isFirstCahar) {
                if (var == '[') {
                    return reConvertJson;
                } else if (var == '"') {
                    isFirstCahar = false;
                    resultString.append("[");
                    resultString.append(var);
                }
            } else {
                if (var == '"') {
                    resultString.append(var);
                    resultString.append("]");
                    break;
                } else {
                    resultString.append(var);
                }
            }
        }
        resultString.append(reConvertJson.substring(resultString.toString().length() - 2));
        return resultString.toString();
    }

    /**
     * . 用对应的正则表达式判断某个字符串是否符合要求
     *
     * @param src
     *            要判断的字符串
     * @param regEx
     *            正则表达式
     * @return 是否符合正则表达式要求
     */
    public static boolean strIsAccordRegex(String src, String regEx) {
        // 验证
        if (isStringParamNotLegal(src) || isStringParamNotLegal(regEx)) {
            return false;
        }
        return Pattern.matches(regEx, src);
    }

    /**
     * . 判断字符串能否转化为数字(浮点数)
     *
     * @param src
     *            src
     * @return 返回结果
     */
    public static boolean strIsNumber(String src) {
        if (isStringParamNotLegal(src)) {
            return false;
        }
        try {
            Double.parseDouble(src);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * .
     *
     * @param src
     *            src
     * @return 是否是三字码
     */
    public static boolean strIsThreeWords(String src) {
        return strIsAccordRegex(src, "^[A-Z]{3}$");
    }

    /**
     * . 字符串是否是 YYYYmmdd 格式 20开头的
     *
     * @param src
     *            src
     * @return 结果
     */
    public static boolean strIsYYYYmmdd(String src) {
        return strIsAccordRegex(src, "^[0-9]{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$");
    }

    /**
     * . 字符串是否是 HHmm
     *
     * @param src
     *            src
     * @return 结果
     */
    public static boolean strIsHHmm(String src) {
        return strIsAccordRegex(src, "^([01][0-9]|2[0-3])[0-5][0-9]$");
    }

    /**
     * . 判断航空公司大写二字码是否符合要求
     *
     * @param src
     *            src
     * @return 结果
     */
    public static boolean strIsBigTwoWords(String src) {
        return strIsAccordRegex(src, "^[A-Z]{2}$");
    }

    /**
     * String 转Integer.
     *
     * @param param
     *            param
     * @return Integer
     */
    public static Integer getInteger(Object param) {
        if (param != null) {
            String p = (String) param;
            p = p.trim();
            Pattern pattern = Pattern.compile("[0-9]+");
            if (pattern.matcher(p).matches()) {
                return Integer.valueOf(p);
            }
        }
        return null;
    }

    /**
     * TODO 首字母转小写.
     *
     * @param str
     *            .
     * @return .
     */
    public static String firstCharToLower(String str) {
        char[] arr = str.toCharArray();
        if (arr.length > 0 && 65 <= (int) arr[0] && (int) arr[0] <= 90) {
            arr[0] = (char) ((int) arr[0] + 32);
        } else {
            return str;
        }

        return new String(arr);
    }

    /**
     * TODO 首字母转大写.
     *
     * @param str
     *            .
     * @return .
     */
    public static String firstCharToUpper(String str) {
        char[] arr = str.toCharArray();
        if (arr.length > 0 && 65 <= (int) arr[0] && (int) arr[0] <= 90) {
            arr[0] = (char) ((int) arr[0] - 32);
        } else {
            return str;
        }

        return new String(arr);
    }

    /**
     * TODO Unicode字符转化成对应的字符.
     *
     * @param s .
     * @return .
     */
    public static String unicodeToString(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\' && chars[i + 1] == 'u') {
                char cc = 0;
                for (int j = 0; j < 4; j++) {
                    char ch = Character.toLowerCase(chars[i + 2 + j]);
                    if ('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'f') {
                        cc |= Character.digit(ch, 16) << (3 - j) * 4;
                    } else {
                        cc = 0;
                        break;
                    }
                }
                if (cc > 0) {
                    i += 5;
                    sb.append(cc);
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
