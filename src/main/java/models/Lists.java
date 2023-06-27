package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Lists {

    public List<ListOfBoard> allLists;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    @Override
    public String toString() {
        return "Lists{" +
                "allLists=" + allLists +
                '}';
    }
}
