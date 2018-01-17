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

    @OneToMany(mappedBy="kategorie", cascade = CascadeType.ALL, orphanRemoval = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kategorie)) return false;

        Kategorie kategorie = (Kategorie) o;

        if (getId() != kategorie.getId()) return false;
        return getNazev().equals(kategorie.getNazev());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNazev().hashCode();
        return result;
    }
}