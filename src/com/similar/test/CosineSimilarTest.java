package com.similar.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CosineSimilarTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sb = new Scanner(System.in); 
		
		int aCount = 0;
		int bCount = 0;		 
		List<Product> productsA = new ArrayList<Product>();
		List<Product> productsB = new ArrayList<Product>();
		
		System.out.print("输入A用户订单中产品数量："); 
		if (sb.hasNextInt()) {
            // 判断输入的是否是整数
			aCount = sb.nextInt();           
        } else {
            // 输入错误的信息
            System.out.println("输入的不是整数！");
            return;
        }
		
		
		for(int i=0;i<aCount;i++){
			Product product = new Product();
			
			 System.out.print("输入产品编码：");         
		        if (sb.hasNext()) {
		        	product.setProductCode(sb.next());  
		        }
		        System.out.print("输入产品价格：");  
		        if(sb.hasNextDouble()){     	
		        	product.setPrice(sb.nextDouble()); 
		        }
		        System.out.print("输入国籍：");  
		        if (sb.hasNext()) {
		        	product.setCountry(sb.next());  
		        }
		        productsA.add(product);
		        System.out.println("--------------------");
		}
		
		
		System.out.print("输入B用户订单中产品数量："); 
		if (sb.hasNextInt()) {
            // 判断输入的是否是整数
			bCount = sb.nextInt();           
        } else {
            // 输入错误的信息
            System.out.println("输入的不是整数！");
            return;
        }
		
		for(int i=0;i<bCount;i++){
			Product product = new Product();
			
			 System.out.print("输入产品编码：");         
		        if (sb.hasNext()) {
		        	product.setProductCode(sb.next());  
		        }
		        System.out.print("输入产品价格：");  
		        if(sb.hasNextDouble()){     	
		        	product.setPrice(sb.nextDouble()); 
		        }
		        System.out.print("输入国籍：");  
		        if (sb.hasNext()) {
		        	product.setCountry(sb.next());  
		        }
		        productsB.add(product);
		        System.out.println("--------------------");
		}
				
        sb.close();         //若没有关闭Scanner对象将会出现警告  
        
        System.out.println("");
        System.out.println("---------A商品订单情况-----------");
        for(Product product:productsA){
        	System.out.println(product);
        }
        System.out.println("---------B商品订单情况-----------");
        for(Product product:productsB){
        	System.out.println(product);
        }
        
        System.out.println("");
        Double cosSimilar = cosSimilarityByProduct(productsA,productsB); //计算A和B的余弦相似
        System.out.println("A和B的余弦相似："+cosSimilar);

	}
	
	/**
	 * 
	* @Title: cosSimilarityByString
	* @Description: 得到两个商品的相似性
	* @param @param first
	* @param @param second
	* @param @return    
	* @return Double   
	* @throws
	 */
	public static Double cosSimilarityByProduct(List<Product> productA,List<Product> productB){
		try{
			Map<String, Integer> firstTfMap = segProductStr(productA);
			Map<String, Integer> secondTfMap = segProductStr(productB);
			if(firstTfMap.size()<secondTfMap.size()){
				Map<String, Integer> temp=firstTfMap;
				firstTfMap=secondTfMap;
				secondTfMap=temp;
			}
			return calculateCos((LinkedHashMap<String, Integer>)firstTfMap, (LinkedHashMap<String, Integer>)secondTfMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0d;
	}
	
	
    /**
     * 
    * @Title: segProductStr
    * @Description: 返回  商品（商品编码、商品价格、国别）  LinkedHashMap的分词
    * @param @param content
    * @param @return    
    * @return Map<String,Integer>   
    * @throws
     */
    public static Map<String, Integer> segProductStr(List<Product> products){
      
        Map<String, Integer> words = new LinkedHashMap<String, Integer>();
        try {
        	if(products == null || products.size() == 0){
        		throw new RuntimeException("产品信息不能为空！");
        	}
        	for(Product product : products){
        		if (words.containsKey(product.getProductCode())) {
                    words.put(product.getProductCode(), words.get(product.getProductCode()) + 1);
                } else {
                    words.put(product.getProductCode(), 1);
                }
        		
        		if (words.containsKey(product.getPrice())) {
                    words.put(product.getPrice()+"", words.get(product.getPrice()+"") + 1);
                } else {
                    words.put(product.getPrice()+"", 1);
                }
        		
        		if (words.containsKey(product.getCountry())) {
                    words.put(product.getCountry(), words.get(product.getCountry()) + 1);
                } else {
                    words.put(product.getCountry(), 1);
                }
        	}
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return words;
    }    
    
	/**
	 * 
	* @Title: calculateCos
	* @Description: 计算余弦相似性
	* @param @param first
	* @param @param second
	* @param @return    
	* @return Double   
	* @throws
	 */
	private static Double calculateCos(LinkedHashMap<String, Integer> first,LinkedHashMap<String, Integer> second){
		
		List<Map.Entry<String, Integer>> firstList = new ArrayList<Map.Entry<String, Integer>>(first.entrySet());
		List<Map.Entry<String, Integer>> secondList = new ArrayList<Map.Entry<String, Integer>>(second.entrySet());
		//计算相似度  
        double vectorFirstModulo = 0.00;//向量1的模  
        double vectorSecondModulo = 0.00;//向量2的模  
        double vectorProduct = 0.00; //向量积  
        int secondSize=second.size();
		for(int i=0;i<firstList.size();i++){
			if(i<secondSize){
				vectorSecondModulo+=secondList.get(i).getValue().doubleValue()*secondList.get(i).getValue().doubleValue();
				vectorProduct+=firstList.get(i).getValue().doubleValue()*secondList.get(i).getValue().doubleValue();
			}
			vectorFirstModulo+=firstList.get(i).getValue().doubleValue()*firstList.get(i).getValue().doubleValue();
		}
	   return vectorProduct/(Math.sqrt(vectorFirstModulo)*Math.sqrt(vectorSecondModulo));
	}

}
