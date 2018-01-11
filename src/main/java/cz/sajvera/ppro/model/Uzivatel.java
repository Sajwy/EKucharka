package cz.sajvera.ppro.model;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Uzivatel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(length = 20)
    private String uzivatelskeJmeno;

    @Column(columnDefinition="TEXT")
    private String heslo;

    @Column(length = 30)
    private String jmeno;

    @Column(length = 50)
    private String prijmeni;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy="uzivatel", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @OrderBy("nazev")
    private List<Recept> recepty = new ArrayList<>();

    public Uzivatel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUzivatelskeJmeno() {
        return uzivatelskeJmeno;
    }

    public void setUzivatelskeJmeno(String uzivatelskeJmeno) {
        this.uzivatelskeJmeno = uzivatelskeJmeno;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Recept> getRecepty() {
        return recepty;
    }

    public void setRecepty(List<Recept> recepty) {
        this.recepty = recepty;
    }

    public String vytvorHashHesla(String heslo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(heslo.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}