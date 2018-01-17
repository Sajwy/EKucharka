package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Recept;
import cz.sajvera.ppro.model.Uzivatel;

import java.util.List;

public interface ReceptDao {

    public Recept merge(Recept recept);

    public void save(Recept recept);

    public void delete(Recept recept);

    public Recept findReceptById(int id);

    public List<Recept> findAll();

    public List<Recept> findReceptsByKategorieID(int id);

    public boolean jeIDvDB(int id);

    public boolean maUzivatelOpravneniKReceptu(int id, Uzivatel uzivatel);

    public List<Recept> findReceptsByUzivatel(Uzivatel uzivatel);
    
}