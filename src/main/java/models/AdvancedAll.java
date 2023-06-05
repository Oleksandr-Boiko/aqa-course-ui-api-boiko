package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdvancedAll {
    @SerializedName("results")
    public List<AdvancedAllResults> allValues;

    public String errorMessage;

    @Override
    public String toString() {
        return "Movies{" +
                "allValues=" + allValues +
                '}';
    }
}
