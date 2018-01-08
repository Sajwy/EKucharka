package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Kategorie;

import java.util.List;

public interface KategorieDao {

    public Kategorie merge(Kategorie Kategorie);

    public void save(Kategorie Kategorie);

    public void delete(Kategorie Kategorie);

    public Kategorie findKategorieById(int id);

    public List<Kategorie> findAll();

}
