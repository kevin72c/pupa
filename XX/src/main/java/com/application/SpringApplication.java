package com.application;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by 大胡子Kevin on 2016/5/29.
 */
@Configuration
@ComponentScan
@ImportResource()
public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApplication.class);
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        System.out.println(factory);

    }

//    private static void binaryInsert(int[] ary) {
//        int setValueCount = 0;
//        // 从数组第二个元素开始排序，因为第一个元素本身肯定是已经排好序的
//        for (int j = 1; j < ary.length; j++) {// 复杂度 n
//            // 保存当前值
//            int key = ary[j];
//            // ∆ 利用二分查找定位插入位置
////   int index = binarySearchAsc(ary, ary[j], 0, j - 1);// 复杂度：O(logn)
////   int index = binarySearchDesc(ary, ary[j], 0, j - 1);// 复杂度：O(logn)
//            int index = binarySearchDesc2(ary, ary[j], 0, j - 1);// 复杂度：O(logn)
//            printArray(ary);
//            System.out.println("第" + j +"个索引上的元素要插入的位置是：" + index);
//            // 将目标插入位置，同时右移目标位置右边的元素
//            for (int i = j; i > index; i--) {// 复杂度,最差情况：(n-1)+(n-2)+...+n/2=O(n^2)
//                ary[i] = ary[i - 1]; //i-1 <==> index
//                setValueCount++;
//            }
//            ary[index] = key;
//            setValueCount++;
//        }
//        System.out.println("\n 设值次数(setValueCount)=====> " + setValueCount);
//    }
    /**
     * 二分查找 降序， 非递归
     */
    private static int binarySearchDesc2(int[] ary, int target, int from, int to) {
        for (; from < to; ) {
            int mid = (from + to) >>> 1;
            if (ary[mid] > target) {
                from = mid + 1;
            } else {
                to = mid -1;
            }
        }
        //from <==> to;
        if (ary[from] > target) {//如 5,4, 要插入的是4
            return from + 1;
        } else {
            return from;
        }
    }
}
