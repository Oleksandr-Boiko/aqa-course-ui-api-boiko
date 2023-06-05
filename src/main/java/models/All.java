package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class All {

    @SerializedName("results")
    public List<AllResults> allValue;

    public String errorMessage;

    @Override
    public String toString() {
        return "Movies{" +
                "allValue=" + allValue +
                '}';
    }
}
