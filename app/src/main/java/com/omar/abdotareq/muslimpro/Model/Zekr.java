package com.omar.abdotareq.muslimpro.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Zekr implements Serializable {


    private int id ;
    private String title ;
    private String titleNoTa4kel ;
    private int favourite ;
    private ArrayList<Doaa> doaas = new ArrayList<>();
    private int numberOfDoaa ;

    public Zekr(int id, String title, String titleNoTa4kel, int favourite, ArrayList<Doaa> doaas) {
        this.id = id;
        this.title = title;
        this.titleNoTa4kel = titleNoTa4kel;
        this.favourite = favourite;
        this.doaas = doaas;
    }

    public Zekr(String title) {
        this.title = title;
    }

    public Zekr() {
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

    public String getTitleNoTa4kel() {
        return titleNoTa4kel;
    }

    public void setTitleNoTa4kel(String titleNoTa4kel) {
        this.titleNoTa4kel = titleNoTa4kel;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public ArrayList<Doaa> getDoaas() {
        return doaas;
    }

    public void setDoaas(ArrayList<Doaa> doaas) {
        this.doaas .addAll(doaas);
    }

    public int getNumberOfDoaa() {
        return numberOfDoaa;
    }

    public void setNumberOfDoaa(int numberOfDoaa) {
        this.numberOfDoaa = numberOfDoaa;
    }

    public String print(){
        String temp ;
        temp = "id = " + id ;
        temp += "\ntitle = " + title ;
        temp += "\nfavourite = " + favourite ;
        temp += "\nnumberOfDoaa = " + numberOfDoaa ;
        for(int i = 0 ; i < doaas.size() ; i++) {
            temp += "\nDoaa = " + doaas.get(i).print();
        }

        return temp;

    }

}
