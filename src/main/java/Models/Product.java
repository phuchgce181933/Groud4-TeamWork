package Models;

public class Product {
    private int PID;
    private String PName;
    private String details;
    private double price;
    private int quantity;
    private int CatID;
    private String image;
    private int ProductStatic;
    // Constructor không tham số
    public Product() {
    }

    // Constructor đầy đủ
    public Product(int PID, String PName, String details, double Price, int Quantity, int CatID, String image) {
        this.PID = PID;
        this.PName = PName;
        this.details = details;
        this.price = Price;
        this.quantity = Quantity;
        this.CatID = CatID;
        this.image = image;
        
    }

    public Product(int aInt, String string, String string0, double aDouble, int aInt0, int aInt1) {       
    }

    // Getter và Setter
    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double Price) {
        this.price = Price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }

    public int getCatID() {
        return CatID;
    }

    public void setCatID(int CatID) {
        this.CatID = CatID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProductStatic() {
        return ProductStatic;
    }

    public void setProductStatic(int ProductStatic) {
        this.ProductStatic = ProductStatic;
    }
    
}
