package Models;

public class Category {
    private int catID;
    private String catName;
    private String description;
    private String image;

    public Category() {}

    // Constructor với 4 tham số (giữ nguyên)
    public Category(int catID, String catName, String description, String image) {
        this.catID = catID;
        this.catName = catName;
        this.description = description;
        this.image = image;
    }

    // Constructor với 3 tham số (THÊM MỚI)
    public Category(int catID, String catName, String description) {
        this.catID = catID;
        this.catName = catName;
        this.description = description;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category{"
                + "catID=" + catID
                + ", catName='" + catName + '\''
                + ", description='" + description + '\''
                + ", image='" + image + '\''
                + '}';
    }
}
