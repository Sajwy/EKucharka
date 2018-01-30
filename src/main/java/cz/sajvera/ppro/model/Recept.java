package cz.sajvera.ppro.model;

import cz.sajvera.ppro.utils.ImageUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Recept implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(length = 150)
    private String nazev;

    @Column(columnDefinition="TEXT")
    private String postup;

    private int pocetPorci;

    private int dobaPripravy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPridani = new Date();

    @ManyToOne
    private Kategorie kategorie;

    @ManyToOne
    private Uzivatel uzivatel;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Fotka fotka;

    @OneToMany(mappedBy="recept", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id")
    private List<Surovina> suroviny = new ArrayList<>();

    @OneToMany(mappedBy="recept", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("datumPridani DESC")
    private List<Komentar> komentare = new ArrayList<>();

    public Recept() {
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

    public String getPostup() {
        return postup;
    }

    public void setPostup(String postup) {
        this.postup = postup;
    }

    public int getPocetPorci() {
        return pocetPorci;
    }

    public void setPocetPorci(int pocetPorci) {
        this.pocetPorci = pocetPorci;
    }

    public int getDobaPripravy() {
        return dobaPripravy;
    }

    public void setDobaPripravy(int dobaPripravy) {
        this.dobaPripravy = dobaPripravy;
    }

    public Date getDatumPridani() {
        return datumPridani;
    }

    public String getDatumPridaniString() {
        SimpleDateFormat format = new SimpleDateFormat("dd. MM. yyyy HH:mm:ss");
        return format.format(this.datumPridani);
    }

    public void setDatumPridani(Date datumPridani) {
        this.datumPridani = datumPridani;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        if(this.kategorie != null)
            this.kategorie.getRecepty().remove(this);
        this.kategorie = kategorie;
        kategorie.getRecepty().add(0,this);
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
        uzivatel.getRecepty().add(0,this);
    }

    public Fotka getFotka() {
        return fotka;
    }

    public void setFotka(Fotka fotka) {
        if(this.fotka != null) {
            ImageUtils.smazFotku(this.fotka);
        }
        this.fotka = fotka;
        if(fotka != null)
            fotka.setRecept(this);
    }

    public List<Surovina> getSuroviny() {
        return suroviny;
    }

    public void setSuroviny(List<Surovina> suroviny) {
        this.suroviny = suroviny;
    }

    public void addSurovina(Surovina surovina) {
        suroviny.add(surovina);
        surovina.setRecept(this);
    }

    public void removeSurovina(Surovina surovina) {
        suroviny.remove(surovina);
        surovina.setRecept(null);
    }

    public List<Komentar> getKomentare() {
        return komentare;
    }

    public void setKomentare(List<Komentar> komentare) {
        this.komentare = komentare;
    }
}