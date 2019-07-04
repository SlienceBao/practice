package com.jj0327.practice.leetcode;

import cn.hutool.core.util.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinbao
 * @date 2019/7/1 17:01
 * @description: 算数题
 */
public class MathLeetCode {

    public static void main(String[] args) {

        // 9 回文数
        System.out.println("回文数: " + isPalindrome(10010));
        System.out.println("回文数1: " + isPalindrome1(10010));
        // 13 罗马数字 转 阿拉伯数字
        System.out.println("罗马数字 转 阿拉伯数字: " + parseSymbolNum("XVII"));
    }

    /**
     * 9 回文数 Palindrome Number
     * 例:
     * 121 true
     * 123 false
     * -121 false
     */
    /**
     * 解法一
     * 比较翻转后的两个字符串是否相等
     *
     * @param num
     * @return
     */
    private static boolean isPalindrome(int num) {
        if (num < 0) {
            return false;
        }
        String numStr = String.valueOf(num);
        char[] chars = numStr.toCharArray();
        String s = new String(ArrayUtil.reverse(chars));
        return numStr.equals(s);
    }

    /**
     * 回文数解法2
     * 比较对称位置
     *
     * @param num
     * @return 是否为回文数
     */
    private static boolean isPalindrome1(int num) {
        if (num < 0) {
            return false;
        }
        String numStr = String.valueOf(num);
        char[] chars = numStr.toCharArray();
        int length = chars.length;
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 罗马数字
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * <p>
     * 思路 左边小于右边标识减去该数值
     */
    private static int parseSymbolNum(String symbol) {
        Map<Character, Integer> rule = new HashMap<>(7);
        rule.put('I', 1);
        rule.put('V', 5);
        rule.put('X', 10);
        rule.put('L', 50);
        rule.put('C', 100);
        rule.put('D', 500);
        rule.put('M', 1000);

        char[] chars = symbol.toCharArray();
        int sum = rule.get(chars[chars.length - 1]);
        for (int i = 0; i < chars.length - 1; i++) {
            if (rule.get(chars[i]) < rule.get(chars[i + 1])) {
                sum -= rule.get(chars[i]);
            } else {
                sum += rule.get(chars[i]);
            }
        }
        return sum;
    }



}
