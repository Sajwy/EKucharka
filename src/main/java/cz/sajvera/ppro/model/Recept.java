package cz.sajvera.ppro.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Size(min = 1, max = 150)
    @Column(nullable = false)
    private String nazev;

    @Column(nullable = false, columnDefinition="TEXT")
    private String postup;

    @Column(nullable = false)
    private int pocetPorci;

    @Column(nullable = false)
    private int dobaPripravy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date datumPridani = new Date();

    @ManyToOne
    private Kategorie kategorie;

    @ManyToOne
    private Uzivatel uzivatel;

    @OneToOne
    private Fotka fotka;

    @OneToMany(mappedBy="recept")
    private List<Surovina> suroviny = new ArrayList<>();

    @OneToMany(mappedBy="recept")
    private List<Komentar> komentare = new ArrayList<>();

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

    public void setDatumPridani(Date datumPridani) {
        this.datumPridani = datumPridani;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Fotka getFotka() {
        return fotka;
    }

    public void setFotka(Fotka fotka) {
        this.fotka = fotka;
    }

    public List<Surovina> getSuroviny() {
        return suroviny;
    }

    public void setSuroviny(List<Surovina> suroviny) {
        this.suroviny = suroviny;
    }

    public List<Komentar> getKomentare() {
        return komentare;
    }

    public void setKomentare(List<Komentar> komentare) {
        this.komentare = komentare;
    }
}
