/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Huynh Gia Phuc - CE181933
 */
public class Product {
    private int PID;
    private String PName ;
    private String Details;
    private double Price;
    private int Quantity;
    private int CatID;
    private String Image;

    public Product() {
    }

    public Product(int PID, String PName, String Details, Double Price, int Quantity, int CatID, String Image) {
        this.PID = PID;
        this.PName = PName;
        this.Details = Details;
        this.Price = Price;
        this.Quantity = Quantity;
        this.CatID = CatID;
        this.Image = Image;
    }

    public Product(int aInt, String string, String string0, double aDouble, int aInt0, int aInt1) {
      
    }

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
        return Details;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getCatID() {
        return CatID;
    }

    public void setCatID(int CatID) {
        this.CatID = CatID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    
    
    
    
}
