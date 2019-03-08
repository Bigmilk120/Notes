package com.example.Notes;

import java.sql.Date;

public class InsertNote {
    public Date date;
    public String note_text;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

}
