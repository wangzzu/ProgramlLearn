package javabasic.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by wangmeng on 20/12/2016.
 */
public class HashMapIterate {
    private static Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) {
        map.putAll(generateRandomMap(10000000));
        System.out.println("map.size: "+map.size());

        long start1 = System.currentTimeMillis();
        iterateMap1(map);
        long end1 = System.currentTimeMillis();
        System.out.println("第一种方法遍历花费时间（ms）: " + (end1 - start1));

        long start2 = System.currentTimeMillis();
        iterateMap2(map);
        long end2 = System.currentTimeMillis();
        System.out.println("第二种方法遍历花费时间（ms）: " + (end2 - start2));

        long start3 = System.currentTimeMillis();
        iterateMap3(map);
        long end3 = System.currentTimeMillis();
        System.out.println("第三种方法遍历花费时间（ms）: " + (end3 - start3));

        long start4 = System.currentTimeMillis();
        iterateMap4(map);
        long end4 = System.currentTimeMillis();
        System.out.println("第四种方法遍历花费时间（ms）: " + (end4 - start4));
    }

    /**
     * generate a random map to test
     *
     * @param size the map.size
     */
    public static Map<String, Integer> generateRandomMap(int size) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int digit = (int) (Math.random() * 100);
        String msg=null;
        while (size > 0) {
            msg = getRandomString(digit);
            map.put(msg, digit);
            size--;
        }
        return map;
    }

    /**
     * generate a random string
     *
     * @param length string.length
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return new String(buf);
    }

    /**
     * 1. use map.entrySet() to iterate the map
     */
    public static void iterateMap1(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
    }

    /**
     * 2. 调用map.entrySet()的集合迭代器
     */
    public static void iterateMap2(Map<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            entry.getKey();
            entry.getValue();
        }
    }

    /**
     * 3. for each map.keySet()，再调用get获取
     */
    public static void iterateMap3(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            map.get(key);
        }
    }

    /**
     * 4. for each map.entrySet()，用临时变量保存map.entrySet()
     */
    public static void iterateMap4(Map<String, Integer> map) {
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            entry.getKey();
            entry.getValue();
        }
    }
}
