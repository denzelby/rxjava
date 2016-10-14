package model;

public abstract class Drink {

    private String name;
    private String country;
    private float price;

    public Drink(String name, String country, float price) {
        this.name = name;
        this.country = country;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public float getPrice() {
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String toString() {
        return "model.Drink(name=" + this.getName() + ", country=" + this.getCountry() + ", price=" + this.getPrice() + ")";
    }
}
