package models;

public class AdvancedAllResults {
    public String id;
    public String title;
    public String description;
    public String genres;

    @Override
    public String toString() {
        return "AdvancedAllResults{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genres='" + genres + '\'' +
                '}';
    }
}
