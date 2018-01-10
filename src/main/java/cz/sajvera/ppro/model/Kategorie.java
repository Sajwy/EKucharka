package cz.sajvera.ppro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kategorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(length = 30)
    private String nazev;

    @OneToMany(mappedBy="kategorie", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @OrderBy("datumPridani")
    private List<Recept> recepty = new ArrayList<>();

    public Kategorie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int idKategorie) {
        this.id = idKategorie;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public List<Recept> getRecepty() {
        return recepty;
    }

    public void setRecepty(List<Recept> recepty) {
        this.recepty = recepty;
    }
}