package com.omar.abdotareq.muslimpro.Model;

import java.io.Serializable;

public class Doaa implements Serializable {

    private int id ;
    private String text ;
    private String teller ;
    private int headID ;
    private int number ;

    public Doaa(int id, String text, String teller, int headID, int number) {
        this.id = id;
        this.text = text;
        this.teller = teller;
        this.headID = headID;
        this.number = number;
    }

    public Doaa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getHeadID() {
        return headID;
    }

    public void setHeadID(int headID) {
        this.headID = headID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String print(){

        String temp ;
        temp = "{ id = " + id ;
        temp += "\ntext = " + text ;
        temp += "\nteller = " + teller ;
        temp += "\nheadID = " + headID ;
        temp += "\nnumber = " + number + " }\n" ;

        return temp;

    }
}
