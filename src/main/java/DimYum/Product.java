package DimYum;

public class Product {
    //encapsulation
    private String name;
    private int price;
    private int quantity;
    private boolean selected;
    private String imagePath; // Tambahkan ini

    //constructor
    public Product(String name, int price, int quantity, String imagePath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.selected = false;
        this.imagePath = imagePath;
    }

    // Getter dan Setter
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isSelected() { return selected; }
    public String getImagePath() { return imagePath; } // Tambahkan ini
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSelected(boolean selected) { this.selected = selected; }

    //method tambahan
    public int getTotalPrice() {
        return price * quantity;
    }
}