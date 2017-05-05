package com.example.a00687560.util;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xpf on 2017/5/1 :)
 * GitHub:xinpengfei520
 * Function:转换工具类
 */

public class StringConvert {

    /**
     * 将string字符串转换为set<Integer>集合
     *
     * @param str
     * @return
     */
    public static Set<Integer> StringToSet(String str) {
        Set<Integer> set = new HashSet<>();
        String replace = str.replace(" ", "");
        char[] chars = replace.toCharArray();
        for (int i = 1; i < chars.length; i += 2) {
            char aChar = chars[i];
            Log.e("TAG", "aChar===" + aChar);
            if (",".equals("" + aChar) || "[".equals("" + aChar) || "]".equals("" + aChar)) {
                continue;
            }
            set.add(Integer.parseInt("" + aChar));
        }
        return set;
    }
}
