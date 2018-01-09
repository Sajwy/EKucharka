package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Recept;

import java.util.List;

public interface ReceptDao {

    public Recept merge(Recept recept);

    public void save(Recept recept);

    public void delete(Recept recept);

    public Recept findReceptById(int id);

    public List<Recept> findAll();
    
}