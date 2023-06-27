package models;

public class Organization {

    public String name;
    public String id;

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
