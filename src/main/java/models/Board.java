package models;

public class Board {

    public String id;
    public String name;
    public String desc;
    public String idOrganization;

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", idOrganization='" + idOrganization + '\'' +
                '}';
    }
}
