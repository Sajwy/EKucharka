package cz.sajvera.ppro.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Uzivatel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Size(min = 1, max = 20)
    @Column(nullable = false)
    private String uzivatelskeJmeno;

    @Column(nullable = false, columnDefinition="TEXT")
    private String heslo;

    @Size(min = 1, max = 30)
    @Column(nullable = false)
    private String jmeno;

    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String prijmeni;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy="uzivatel")
    private List<Recept> recepty = new ArrayList<>();

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
}
