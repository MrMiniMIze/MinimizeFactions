package com.minimize.factions.util;

/**
 * Utility to implode strings
 * Author: minimize
 */
public class TextUtil {

    public static String implode(String[] arr, int startIndex, String glue) {
        if (arr.length <= startIndex) return "";
        StringBuilder sb = new StringBuilder(arr[startIndex]);
        for (int i = startIndex + 1; i < arr.length; i++) {
            sb.append(glue).append(arr[i]);
        }
        return sb.toString();
    }
}
