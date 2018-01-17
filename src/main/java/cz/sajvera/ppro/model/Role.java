package cz.sajvera.ppro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(length = 30)
    private String nazev;

    @OneToMany(mappedBy="role")
    @OrderBy("prijmeni, jmeno, id")
    private List<Uzivatel> uzivatele = new ArrayList<>();

    public Role() {
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

    public List<Uzivatel> getUzivatele() {
        return uzivatele;
    }

    public void setUzivatele(List<Uzivatel> uzivatele) {
        this.uzivatele = uzivatele;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (getId() != role.getId()) return false;
        return getNazev().equals(role.getNazev());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNazev().hashCode();
        return result;
    }
}