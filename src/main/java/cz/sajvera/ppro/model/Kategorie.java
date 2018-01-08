package cz.sajvera.ppro.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Kategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Size(min = 1, max = 30)
    @Column(nullable = false)
    private String nazev;

    @OneToMany(mappedBy="kategorie")
    private List<Recept> recepty = new ArrayList<>();

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
