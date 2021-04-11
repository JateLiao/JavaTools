package com.liaoshijie.tools.tinman.wechat_tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/3/26 上午10:15
 */
public class RegexTest {
    public static final Pattern pattern = Pattern.compile("(https?.*?)(?=http|$|<|>|\\s|,)");

    /**
     * main method.
     **/
    public static void main(String[] args) {
        String txt = "@所有人        \n" +
                "欢迎新进群的家长同学们：\n" +
                "今日领取的名单已经整理完毕，请家长们核对下\n" +
                "石佳幸、赵若含、靳倩、刘子豪、万宥宥、朱蕾、煜泽、孟瑞欣、董一嘉、吴涵、孟祥壮、黎紫琳，胡于华，刘梓涵，晨晨,候凯璐，聂伟,李晨旭，徐雅芬，邹一帆，许圣霖，宫欣怡，蔡璇，王婷婷，徐彬凯，冯波，江浦舟，林佩萱，许婉等部分同学名单\n" +
                "\n" +
                "【入群报名任务】·没有报名的将被移出群聊 视为放弃学习名额\n" +
                "\n" +
                "名单上没有的家长请点下方蓝色文字链接，为孩子申请报名，请及时回复【报名成功】或者【111】领取学习资料️️\n" +
                "\n" +
                "\n" +
                "点击下方报名打卡\n" +
                " https://hkyx.mike-x.com/v4lTh\n" +
                "\n" +
                "点击下方报名打卡\n" +
                " https://hkyx.mike-x.com/v4lTh\n" +
                "\n" +
                "点击下方报名打卡\n" +
                " https://hkyx.mike-x.com/v4lTh\n" +
                "\n" +
                "-----------------------\n" +
                "\n" +
                "完成报名的家长及时回复【报名成功】 或者扣【111】即可️️\n" +
                "\n" +
                "注意：完成打卡任务才能参与活动哦！\n" +
                "" +
                "打卡时间截止今晚12点！！！";
        Matcher matcher = pattern.matcher(txt);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
