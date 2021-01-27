package com.nullspace.textsharecore;

import com.fasterxml.jackson.annotation.*;

public class TextEntry {
    @JsonProperty("_id") String id;
    @JsonProperty("date") String date;
    @JsonProperty("text") String text;

    public TextEntry(String id, String date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public TextEntry(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
