package com.similar.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CosineSimilarAlgorithm {

    /**
     * @param @param  first
     * @param @param  second
     * @param @return
     * @return Double
     * @throws
     * @Title: cosSimilarityByString
     * @Description: 得到两个用户的相似性
     */
    public static Double cosSimilarityByProduct(List<Product> productA, List<Product> productB) {
        try {
            Map<String, Integer> firstTfMap = segProductStr(productA);
            Map<String, Integer> secondTfMap = segProductStr(productB);
            if (firstTfMap.size() < secondTfMap.size()) {
                Map<String, Integer> temp = firstTfMap;
                firstTfMap = secondTfMap;
                secondTfMap = temp;
            }
            return calculateCos((LinkedHashMap<String, Integer>) firstTfMap, (LinkedHashMap<String, Integer>) secondTfMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0d;
    }


    /**
     * @param @param  content
     * @param @return
     * @return Map<String,Integer>
     * @throws
     * @Title: segProductStr
     * @Description: 返回  订单（商品编码、商品价格、国别）  LinkedHashMap的分词
     */
    public static Map<String, Integer> segProductStr(List<Product> products) {

        Map<String, Integer> words = new LinkedHashMap<String, Integer>();
        try {
            if (products == null || products.size() == 0) {
                throw new RuntimeException("产品信息不能为空！");
            }
            for (Product product : products) {
                if (words.containsKey(product.getProductCode())) {
                    words.put(product.getProductCode(), words.get(product.getProductCode()) + 1);
                } else {
                    words.put(product.getProductCode(), 1);
                }

                if (words.containsKey(product.getPrice().toString())) {
                    words.put(product.getPrice().toString(), words.get(product.getPrice().toString()) + 1);
                } else {
                    words.put(product.getPrice().toString(), 1);
                }

                if (words.containsKey(product.getCountry())) {
                    words.put(product.getCountry(), words.get(product.getCountry()) + 1);
                } else {
                    words.put(product.getCountry(), 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * @param @param  first
     * @param @param  second
     * @param @return
     * @return Double
     * @throws
     * @Title: calculateCos
     * @Description: 计算余弦相似性
     */
    private static Double calculateCos(LinkedHashMap<String, Integer> first, LinkedHashMap<String, Integer> second) {

        List<Map.Entry<String, Integer>> firstList = new ArrayList<Map.Entry<String, Integer>>(first.entrySet());
        List<Map.Entry<String, Integer>> secondList = new ArrayList<Map.Entry<String, Integer>>(second.entrySet());
        //计算相似度
        double vectorFirstModulo = 0.00;//向量1的模  
        double vectorSecondModulo = 0.00;//向量2的模  
        double vectorProduct = 0.00; //向量积  
        int secondSize = second.size();
        for (int i = 0; i < firstList.size(); i++) {
            if (i < secondSize) {
                vectorSecondModulo += secondList.get(i).getValue().doubleValue() * secondList.get(i).getValue().doubleValue();
                vectorProduct += firstList.get(i).getValue().doubleValue() * secondList.get(i).getValue().doubleValue();
            }
            vectorFirstModulo += firstList.get(i).getValue().doubleValue() * firstList.get(i).getValue().doubleValue();
        }
        return vectorProduct / (Math.sqrt(vectorFirstModulo) * Math.sqrt(vectorSecondModulo));
    }

}
