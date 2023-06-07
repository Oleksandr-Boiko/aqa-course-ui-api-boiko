package models;

public class Card {
    public String id;
    public String name;

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
