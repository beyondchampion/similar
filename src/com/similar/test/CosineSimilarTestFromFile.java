package com.similar.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CosineSimilarTestFromFile {

	public static void main(String[] args) {
		
		List<Product> productsA = new ArrayList<Product>();
		List<Product> productsB = new ArrayList<Product>();
		
		String contents = readFileByChars("similar/big.txt");
		String[] content = contents.split("\\{");
		if(content.length == 0){
			throw new RuntimeException("文件格式不正确！");
		}else{			
			
			String[] productContentA = content[1].split("\\[");
			for(int i=1;i<productContentA.length;i++){
				Product product = new Product();
				product.setProductCode(productContentA[i].substring(productContentA[i].indexOf("productCode=")+12, productContentA[i].indexOf(", price=")));
				product.setPrice(Double.parseDouble(productContentA[i].substring(productContentA[i].indexOf("price=")+6, productContentA[i].indexOf(", country="))));
				product.setCountry(productContentA[i].substring(productContentA[i].indexOf("country=")+8, productContentA[i].indexOf("]")));
				System.out.println("---------A商品第"+i+"个订单情况-----------");
				System.out.println(product);
				productsA.add(product);
			}
			
			String[] productContentB = content[2].split("\\[");
			for(int i=1;i<productContentB.length;i++){
				Product product = new Product();
				product.setProductCode(productContentB[i].substring(productContentB[i].indexOf("productCode=")+12, productContentB[i].indexOf(", price=")));
				product.setPrice(Double.parseDouble(productContentB[i].substring(productContentB[i].indexOf("price=")+6, productContentB[i].indexOf(", country="))));
				product.setCountry(productContentB[i].substring(productContentB[i].indexOf("country=")+8, productContentB[i].indexOf("]")));
				System.out.println("---------B商品第"+i+"个订单情况-----------");
				System.out.println(product);
				productsB.add(product);
			}
						
		}
		
		System.out.println("");
        Double cosSimilar = CosineSimilarAlgorithmByQualification.cosSimilarityByProduct(productsA,productsB); //计算A和B的余弦相似
        System.out.println("A和B的余弦相似："+cosSimilar); 

	}

	
	 /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static String readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        StringBuffer sb = new StringBuffer();
        String content = "";
        try {

			System.out.println(file.getCanonicalPath());//获取标准的路径
			System.out.println(file.getAbsolutePath());//获取绝对路径
//            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
//                    System.out.print((char) tempchar);
                	sb.append((char) tempchar);
                }
            }
            reader.close();
            content = sb.toString();
//            System.out.print("content==="+content);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }


}
