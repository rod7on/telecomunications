package com.comunications.telecomunicationsui;

public class User {
    private int id;
    private String nume;
    private String username;
    private String dateOfBirth;
    private String telephoneNumber;
    private String email;
    private String passwordUser;

    // Constructor
    public User(int id, String nume, String username, String dateOfBirth, String telephoneNumber, String email, String passwordUser) {
        this.id = id;
        this.nume = nume;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.passwordUser = passwordUser;
    }

    // Getter și Setter pentru id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter și Setter pentru nume
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    // Getter și Setter pentru username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter și Setter pentru dateOfBirth
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Getter și Setter pentru telephoneNumber
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    // Getter și Setter pentru email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter și Setter pentru passwordUser
    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}
