package cz.sajvera.ppro.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Surovina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(length = 30)
    private String nazev;

    @Column(length = 30)
    private String mnozstvi;

    @ManyToOne
    private Recept recept;

    public Surovina() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(String mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }
}
