/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;

/**
 *
 * @author Huynh Gia Phuc - CE181933
 */
public class Cart {
    private int CartID;
    private int UserID;
    private String Pname;
    private double Price;
    private int PID;
    private int Quantity;
    private String Image; 
    private int ProductStatic;
    public Cart() {
    }

    public Cart(int CartID, int UserID, String Pname, double Price, int PID, int Quantity, String Image, int ProductStatic) {
        this.CartID = CartID;
        this.UserID = UserID;
        this.Pname = Pname;
        this.Price = Price;
        this.PID = PID;
        this.Quantity = Quantity;
        this.Image = Image;
        this.ProductStatic = ProductStatic;
    }

    public int getProductStatic() {
        return ProductStatic;
    }

    public void setProductStatic(int ProductStatic) {
        this.ProductStatic = ProductStatic;
    }

   

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

   

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String Pname) {
        this.Pname = Pname;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    
  
    
}
