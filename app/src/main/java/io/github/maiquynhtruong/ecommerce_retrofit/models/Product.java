package io.github.maiquynhtruong.ecommerce_retrofit.models;

import com.google.gson.annotations.SerializedName;

/**
  Example API call:
 https://api.zappos.com/Search?term=nike&key=b743e26728e16b81da139182bb2094357c31d331
 */

public class Product {
    @SerializedName("productName")
    private String productName = "";
    @SerializedName("originalPrice")
    private String originalPrice = "";
    @SerializedName("ProductId")
    private String productId = "";
    @SerializedName("price")
    private String price = "";
    @SerializedName("percentOff")
    private String percentOff = "";
    @SerializedName("productUrl")
    private String productUrl = "";
    @SerializedName("thumbnailImageUrl")
    private String thumbnailImageUrl = "";

    /** Constructor**/
    public Product(String productName, String original_price, String price, String productId,
                   String percentOff, String productUrl, String thumbnailImageUrl) {
        this.productName = productName;
        this.productId = productId;
        this.originalPrice = original_price;
        this.price = price;
        this.percentOff = percentOff;
        this.productUrl = productUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
}
