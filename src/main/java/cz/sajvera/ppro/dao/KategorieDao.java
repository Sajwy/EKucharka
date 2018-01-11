package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Kategorie;

import java.util.List;

public interface KategorieDao {

    public Kategorie merge(Kategorie kategorie);

    public void save(Kategorie kategorie);

    public void delete(Kategorie kategorie);

    public Kategorie findKategorieById(int id);

    public List<Kategorie> findAll();

    public boolean jeIDvDB(int id);

}