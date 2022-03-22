package business;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Comparable<MenuItem>, Serializable {

    private String title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public MenuItem(String title) {
        this.title = title;
    }

    public abstract int computePrice();

    public MenuItem(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int compareTo(MenuItem o) {
            return this.title.compareTo(o.getTitle());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        MenuItem menuItem = (MenuItem) o;
        if(!title.equals(menuItem.getTitle()))
            return true;
        return false;
    }

}
