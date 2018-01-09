package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Fotka;

public interface FotkaDao {

    public Fotka merge(Fotka fotka);

    public void save(Fotka fotka);

    public void delete(Fotka fotka);
    
}