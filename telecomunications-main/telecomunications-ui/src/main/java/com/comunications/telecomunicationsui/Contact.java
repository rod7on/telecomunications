package com.comunications.telecomunicationsui;

public class Contact {
    private int id;
    private String nume;
    private String telefon;


    public Contact(int id, String nume, String telefon) {
        this.id = id;
        this.nume = nume;
        this.telefon = telefon;

    }


    // Getters și Setters pentru id, nume, telefon și customName

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
