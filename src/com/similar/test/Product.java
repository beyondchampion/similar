package com.similar.test;

public class Product {
	
	private String productCode; 
	
	private double price;
	
	private String country;
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", price=" + price
				+ ", country=" + country + "]";
	} 
	
	
	

}
