package ru.clevertec.check.model;


public class Product {
    private int id;
    private String description;
    private double price;
    private int quantity;
    private boolean wholeSale;

    public Product(int id, String description, double price, int quantity, boolean wholeSale) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.wholeSale = wholeSale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isWholeSale() {
        return wholeSale;
    }

    public void setWholeSale(boolean wholeSale) {
        this.wholeSale = wholeSale;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantity +
                ", wholesale=" + wholeSale +
                '}';
    }
}
