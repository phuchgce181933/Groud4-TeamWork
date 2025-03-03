/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author phong
 */
public class Category {
    private int catID;
    private String CatName;
    private String Description;

    public Category() {
    }

    public Category(int CatID, String CatName, String Description) {
        this.catID = CatID;
        this.CatName = CatName;
        this.Description = Description;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int CatID) {
        this.catID = CatID;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String CatName) {
        this.CatName = CatName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
}
