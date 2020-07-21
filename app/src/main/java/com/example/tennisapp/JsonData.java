package com.example.tennisapp;

import com.google.gson.annotations.SerializedName;

public class JsonData {

    @SerializedName("first name")
    private String firstName;
    @SerializedName("points")
    private String points;
    @SerializedName("games")
    private String games;
    @SerializedName("sets")
    private String sets;
    @SerializedName("tieBreak points")
    private String tieBreakPoints;

    public String getFirstName() {
        return firstName;
    }

    public String getPoints() {
        return points;
    }

    public String getGames() {
        return games;
    }

    public String getSets() {
        return sets;
    }

    public String getTieBreakPoints() {
        return tieBreakPoints;
    }
}
