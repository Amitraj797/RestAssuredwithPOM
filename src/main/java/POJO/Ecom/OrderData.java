package POJO.Ecom;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderData {
    @JsonProperty("_id")
    private String _id;
    private String productById;
    private String orderBy;
    private String productOrderedById;
    private String productName;
    private String country;
    private String productDescription;
    private String productImageLink;
    private String orderPrice;
    private int __v;

    public String get_Id() {
        return _id;
    }

    public void set_Id(String id) {
        this._id = id;
    }

    public String getProductById() {
        return productById;
    }

    public void setProductById(String productById) {
        this.productById = productById;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getProductOrderedById() {
        return productOrderedById;
    }

    public void setProductOrderedById(String productOrderedById) {
        this.productOrderedById = productOrderedById;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageLink() {
        return productImageLink;
    }

    public void setProductImageLink(String productImageLink) {
        this.productImageLink = productImageLink;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
