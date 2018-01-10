package cz.sajvera.ppro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Komentar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(columnDefinition="TEXT")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPridani = new Date();

    @OneToOne
    private Uzivatel autor;

    @ManyToOne
    private Recept recept;

    public Komentar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDatumPridani() {
        return datumPridani;
    }

    public void setDatumPridani(Date datumPridani) {
        this.datumPridani = datumPridani;
    }

    public Uzivatel getAutor() {
        return autor;
    }

    public void setAutor(Uzivatel autor) {
        this.autor = autor;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }
}