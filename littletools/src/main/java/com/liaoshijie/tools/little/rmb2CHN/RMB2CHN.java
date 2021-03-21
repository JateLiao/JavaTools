/*
 * 文件名：Money2CHN.java
 * 版权：Copyright 2007-2016 KOBE Tech. Co. Ltd. All Rights Reserved.
 * 描述： Money2CHN.java
 * 修改人：KOBE
 * 修改时间：2016年11月8日
 * 修改内容：新增
 */
package com.liaoshijie.tools.little.rmb2CHN;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 金额转汉字大写，没错，我就希望我满身铜臭味。。。
 *
 * @author KOBE
 */
public class RMB2CHN {

    public static void main(String[] args) throws Exception {
        String monry = "8745136548546.1238";
        System.out.println("转换前：" + monry);
        System.err.println("转换后：" + rmb2CHN(monry));
    }

    private static String[] unitArr = {"万", "亿", "万亿", "亿亿"};
    private static String[] singleArr = {"拾", "佰", "仟"};
    private static String[] singleRightArr = {"角", "分", "厘"};
    private static String[] numCapitalArr = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static String unitStr = "亿万仟佰拾角分厘";

    /**
     * 金钱转汉字大写，比如10240.521 --> 壹万零贰佰肆拾圆伍角贰分壹厘
     *
     * 精确到小数点后三位，也就是精确到“厘”，反正一般都够用了
     *
     * 处理要点：依然是0的个数对读法的影响额处理，当0的时候，转化后是会出现“零”的，当不同个数的0连续出现后，读法也是不一样的。。。
     *
     * 壹贰叁肆伍陆柒捌玖拾零，亿，万，佰，元，角，分，厘
     */
    public static String rmb2CHN(String rmb) throws Exception {
        rmb = removeZeroHead(rmb);
        validMoney(rmb); // 验证金钱输入正误
        StringBuffer res = new StringBuffer();

        String[] moneyArr = rmb.split("\\.");
        res.append(constructMoneyLeft(moneyArr[0]));
        res.append(constructMoneyRight(moneyArr[1]));

        return removeZeroCapital(res.toString());
    }

    /**
     * TODO 构造小数点左边整数部分.
     *
     * 每个四位中间出现了0，则在结果里会出现“零”；
     * 下一个四位以0开头，结果会出现0；
     */
    private static String constructMoneyLeft(String strLeft) {
        StringBuffer sb = new StringBuffer();
        int len = strLeft.length();
        int index = 0;

        String[] mArr = new String[(int) Math.ceil(len / 4.0)];
        for (int i = 0; i < len; i++) {
            sb.insert(0, strLeft.charAt(len - i - 1)); // 金钱倒序
            if (i % 4 == 3) {
                mArr[mArr.length - index - 1] = sb.toString(); // 按金钱的阅读顺序存放
                index++;
                sb.setLength(0);
            } else if (i >= len / 4 * 4) { // 
                mArr[0] = strLeft.substring(0, len - i);
                sb.setLength(0);
                break;
            }
        }
        sb.setLength(0);

        String[] unitArrTmp = Arrays.copyOfRange(unitArr, 0, mArr.length - 1);
        for (int i = 0; i < mArr.length; i++) {
            String m = mArr[i];
            int mLen = m.length();
            for (int j = 0; j < mLen; j++) {
                sb.append(getRMBCapital(String.valueOf(m.charAt(j))));
                if (sb.toString().lastIndexOf("零") == sb.length() - 1) {
                    continue;
                }
                sb.append((j < mLen - 1) ? singleArr[mLen - j - 2] : "");
            }

            if (sb.toString().lastIndexOf("零") == sb.length() - 1) {
                sb.setLength(sb.length() - 1);
            }
            int idx = unitArrTmp.length - i - 1;
            if (idx >= 0) {
                sb.append(unitArrTmp[idx]);
            }
        }
        while (sb.toString().lastIndexOf("零") == sb.length() - 1) {
            sb.setLength(sb.length() - 1);
        }
        return sb.append("圆").toString(); // removeZeroCapital(sb.toString());
    }

    /**
     * TODO 构造小数点右边零头部分.
     */
    private static String constructMoneyRight(String strRight) {
        StringBuffer sb = new StringBuffer();
        // 小数点最多保留三位且四舍五入
        BigDecimal value = new BigDecimal("0." + strRight).setScale(3, BigDecimal.ROUND_UP);
        String[] rightArr = value.toString().split("\\.");
        for (int i = 0; i < rightArr[1].toCharArray().length; i++) {
            char c = rightArr[1].charAt(i);
            if (c == '0') {
                continue;
            }
            sb.append(getRMBCapital(String.valueOf(c)));
            sb.append(singleRightArr[i]);
        }
        return sb.toString(); //  removeZeroCapital(sb.toString());
    }

    /**
     * TODO 去掉多余的 “零”.
     */
    private static String removeZeroCapital(String str) {
        str = str.replaceAll("零{1,}", "零");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.toCharArray().length; i++) {
            char c = str.charAt(i);
            if (c == '零') {
                if (unitStr.contains(String.valueOf(str.charAt(i + 1)))) {
                    continue;
                }
            }
            if (str.charAt(Math.max(i, 1) - 1) == '零' && i == str.toCharArray().length - 1) {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * TODO 数字转大写.
     */
    private static String getRMBCapital(String str) {
        return numCapitalArr[Integer.valueOf(str)];
    }

    /**
     * TODO 去掉头部的0，比如：0000514556555去掉后514556555.
     */
    private static String removeZeroHead(String rmb) {
        while (rmb.startsWith("0")) {
            rmb = rmb.substring(1, rmb.length());
        }
        return rmb;
    }

    /**
     * TODO 金额格式.
     */
    private static void validMoney(String rmb) throws Exception {
        if (!rmb.matches("^\\d+(\\.\\d+)?$")) {
            throw new Exception("金额格式输入错误！");
        }
    }
}
