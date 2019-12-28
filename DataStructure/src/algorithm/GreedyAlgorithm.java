package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台
        HashMap<String, HashSet<String>> broadcoasts = new HashMap<String, HashSet<String>>();
        HashSet<String> hashSet1 = new HashSet<>();

        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();

        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();

        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<>();

        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();

        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcoasts.put("K1", hashSet1);
        broadcoasts.put("K2", hashSet2);
        broadcoasts.put("K3", hashSet3);
        broadcoasts.put("K4", hashSet4);
        broadcoasts.put("K5", hashSet5);

        // 存放所有地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 创建ArrayList 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 定义一个临时的集合， 遍历过程中电台覆盖的地区和当前没有覆盖地区的交集
        HashSet<String> tmpSet = new HashSet<>();

        // 定义给 maxKey, 一次遍历中最多能覆盖的为覆盖对应电台的key
        String maxKey = null;
        while (allAreas.size() != 0) {
            maxKey = null;
            for (String key : broadcoasts.keySet()) {
                tmpSet.clear();

                HashSet<String> areas = broadcoasts.get(key);
                tmpSet.addAll(areas);

                tmpSet.retainAll(allAreas);

                if (tmpSet.size() > 0 && (maxKey == null || tmpSet.size() > broadcoasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if(maxKey != null){
                selects.add(maxKey);
                allAreas.removeAll(broadcoasts.get(maxKey));
            }
        }
    }
}
