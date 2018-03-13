package com.similar.test;

public class Product {

    private String productCode;    //商品编码

    private Double price;           //商品价格

    private String country;         //国别

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
