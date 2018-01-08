package cz.sajvera.ppro.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Size(min = 1, max = 30)
    @Column(nullable = false)
    private String nazev;

    @OneToMany(mappedBy="role")
    private List<Uzivatel> uzivatele = new ArrayList<>();

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

    public List<Uzivatel> getUzivatele() {
        return uzivatele;
    }

    public void setUzivatele(List<Uzivatel> uzivatele) {
        this.uzivatele = uzivatele;
    }
}
