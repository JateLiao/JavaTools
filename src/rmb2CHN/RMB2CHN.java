﻿/*
 * 文件名：Money2CHN.java
 * 版权：Copyright 2007-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Money2CHN.java
 * 修改人：KOBE
 * 修改时间：2016年11月8日
 * 修改内容：新增
 */
package rmb2CHN;

/**
 * 金额转汉字大写，没错，我就希望我满身铜臭味。。。
 * 
 * @author KOBE
 */
public class RMB2CHN {

    public static void main(String[] args) throws Exception {
        System.out.println(rmb2CHN("10200501.201"));
    }

    private static String[] unitArr = {"", "", "", ""};

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
        StringBuffer res = new StringBuffer();

        validMoney(rmb); // 验证金钱输入正误

        String[] moneyArr = rmb.split("\\.");

        res.append(constructMoneyLeft(moneyArr[0]));
        res.append(constructMoneyRight(moneyArr[1]));

        return res.toString();
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param rmb
     * @throws Exception 
     */
    private static void validMoney(String rmb) throws Exception {
        if (!rmb.matches("^\\d+(\\.\\d+)?$")) {
            throw new Exception("金额格式输入错误！");
        }
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
                mArr[index] = strLeft.substring(0, len - i);
                sb.setLength(0);
                break;
            }
        }
        sb.setLength(0);

        for (int i = 0; i < mArr.length; i++) {
            String m = mArr[i];
            int mLen = m.length();
            sb.append(getRMBCapital(m));
        }

        return sb.toString();
    }

    /**
     * TODO 添加方法注释.
     * @param m 
     * 
     * @return
     */
    private static String getRMBCapital(String str) {
        String val = null;
        switch (str) {
            case "0":
                val = "零";
                break;
            case "1":
                val = "壹";
                break;
            case "2":
                val = "贰";
                break;
            case "3":
                val = "叁";
                break;
            case "4":
                val = "肆";
                break;
            case "5":
                val = "伍";
                break;
            case "6":
                val = "陆";
                break;
            case "7":
                val = "柒";
                break;
            case "8":
                val = "捌";
                break;
            case "9":
                val = "玖";
                break;
            case "10":
                val = "拾";
                break;

            default:
                break;
        }
        return val;
    }

    /**
     * TODO 构造小数点右边零头部分.
     */
    private static String constructMoneyRight(String strRight) {
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }
}
