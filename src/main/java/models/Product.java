package models;

public class Product {
    private int productId;
    private String productName;
    private String productDesription;
    private int productPrice;

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesription() {
        return productDesription;
    }

    public Product(int productId, String productName, String productDesription, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDesription = productDesription;
        this.productPrice = productPrice;
    }

    public int getProductPrice() {
        return productPrice;

    }
}
