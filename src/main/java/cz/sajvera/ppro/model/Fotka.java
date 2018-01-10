package cz.sajvera.ppro.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Fotka implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(columnDefinition="TEXT")
    private String mala;

    @Column(columnDefinition="TEXT")
    private String stredni;

    @Column(columnDefinition="TEXT")
    private String velka;

    @OneToOne(mappedBy="fotka")
    private Recept recept;

    public Fotka() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMala() {
        return mala;
    }

    public void setMala(String mala) {
        this.mala = mala;
    }

    public String getStredni() {
        return stredni;
    }

    public void setStredni(String stredni) {
        this.stredni = stredni;
    }

    public String getVelka() {
        return velka;
    }

    public void setVelka(String velka) {
        this.velka = velka;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }
}