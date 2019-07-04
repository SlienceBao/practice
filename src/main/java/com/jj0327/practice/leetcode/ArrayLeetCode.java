package com.jj0327.practice.leetcode;

import java.util.HashMap;

/**
 * @author jinbao
 * @date 2019/7/1 17:00
 * @description: 数组题
 */
public class ArrayLeetCode {
    public static void main(String[] args) {
        int[] a = {5, 6, 3, 2, 1};
        System.out.println(findRelativeRanks(a));
    }

    private static Integer[] findRelativeRanks(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    int max = nums[i];
                    nums[i] = nums[j];
                    nums[j] = max;
                }
            }
            hashMap.put(nums[i], i+1);
        }
        System.out.println(hashMap);
        return hashMap.keySet().toArray(new Integer[nums.length]);
    }
}
