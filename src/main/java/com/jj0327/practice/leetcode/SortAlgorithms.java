package com.jj0327.practice.leetcode;

import java.util.Arrays;

/**
 * @author jinbao
 * @date 2019/7/2 14:26
 * @description: 排序算法
 */
public class SortAlgorithms {

    public static void main(String[] args) {
        int[] nums = {3, 1, 8, 2, 9, 6};
        System.out.println("冒泡排序: " + Arrays.toString(bubbleSort(nums)));
        System.out.println("选择排序: " + Arrays.toString(selectSort(nums)));
        System.out.println("插入排序: " + Arrays.toString(insertSort(nums)));
    }

    /**
     * 1. 冒泡排序
     */
    private static int[] bubbleSort(int[] nums) {
        int[] ints = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = 0; j < ints.length - i - 1; j++) {
                // 比较相邻两个元素, 前一个如果大于后一个, 交换位置
                if (ints[j] > ints[j + 1]) {
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
            }
        }
        return ints;
    }

    /**
     * 2. 选择排序
     */
    private static int[] selectSort(int[] nums) {
        int[] ints = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                // 将小的逐次移到前边
                if (ints[i] > ints[j]) {
                    int temp = ints[j];
                    ints[j] = ints[i];
                    ints[i] = temp;
                }
            }
        }
        return ints;
    }

    /**
     * 3. 插入排序
     */
    private static int[] insertSort(int[] nums) {
        int[] ints = Arrays.copyOf(nums, nums.length);
        for (int i = 1; i < ints.length; i++) {
            int anInt = ints[i];
            for (int j = i; j >= 0; --j) {
                if (anInt < ints[j]) {
                    ints[j + 1] = ints[j];
                    ints[j] = anInt;
                }
            }
        }
        return ints;
    }

    /**
     * 3. 快速排序
     */
    private static int[] fastSort(int[] nums) {
        int[] ints = Arrays.copyOf(nums, nums.length);
        return ints;
    }

}
