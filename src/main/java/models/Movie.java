package models;

public class Movie {
    public String id;
    public String title;
    public String description;

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
