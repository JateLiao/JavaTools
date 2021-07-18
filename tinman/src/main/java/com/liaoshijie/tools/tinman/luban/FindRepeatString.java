package com.liaoshijie.tools.tinman.luban;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaoshijie@jojoreading.com
 * @since 2021/6/24 下午7:48
 */
public class FindRepeatString {
    /**
     * main method.
     **/
    public static void main(String[] args) throws IOException {
        String path = "/Users/liaoshijie/Workspace/java/JavaTools/tinman/src/main/java/com/liaoshijie/tools/tinman/luban/ids";
        File file = new File(path);
        List<String> ids = FileUtils.readLines(file, "UTF-8");

        String s = "";
        String[] arr = s.split(",");
        Map<String, Integer> map = new HashMap<>();
        ids.forEach(s1 -> {
            map.put(s1, map.getOrDefault(s1, 0) + 1);
        });
        map.forEach((s1, integer) -> {
            if (integer >= 2) {
                System.out.println(s1);
            }
        });
    }
}
