package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Surovina;

import java.util.List;

public interface SurovinaDao {

    public Surovina merge(Surovina surovina);

    public void save(Surovina surovina);

    public void delete(Surovina surovina);

    public Surovina findSurovinaById(int id);

    public Surovina findSurovinaByNazev(String nazev);

    public List<Surovina> findAll();

}