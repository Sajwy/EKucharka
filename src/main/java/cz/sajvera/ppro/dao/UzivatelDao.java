package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Uzivatel;

import java.util.List;

public interface UzivatelDao {

    public Uzivatel merge(Uzivatel uzivatel);

    public void save(Uzivatel uzivatel);

    public void delete(Uzivatel uzivatel);

    public Uzivatel findUzivatelById(int id);

    public Uzivatel findUzivatelByUzivatelskeJmeno(String uzivatelskeJmeno);

    public List<Uzivatel> findAll();

    public boolean jeUzivatelskeJmenoVolne(String uzivatelskeJmeno);

    public boolean overHashHesla(String hash);
    
}