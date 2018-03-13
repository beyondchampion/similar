package com.similar.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimilarTest {

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

	}

}
