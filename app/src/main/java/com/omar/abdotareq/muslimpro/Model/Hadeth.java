package com.omar.abdotareq.muslimpro.Model;

import java.io.Serializable;

public class Hadeth implements Serializable {

    private int id ;
    private String title ;
    private String text ;
    private String teller ;
    private int favourite ;

    public Hadeth() {
    }

    public Hadeth(int id, String title, String text, String teller, int favourite) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.teller = teller;
        this.favourite = favourite;
    }

    public Hadeth(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTeller() {
        return teller;
    }

    public void setTeller(String teller) {
        this.teller = teller;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
}
