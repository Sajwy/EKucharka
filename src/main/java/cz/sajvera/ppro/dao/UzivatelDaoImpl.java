package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Uzivatel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UzivatelDaoImpl implements UzivatelDao {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Uzivatel merge(Uzivatel uzivatel) {
        return manager.merge(uzivatel);
    }

    @Override
    public void save(Uzivatel uzivatel) {
        manager.persist(uzivatel);
    }

    @Override
    public void delete(Uzivatel uzivatel) {
        manager.remove(uzivatel);
    }

    @Override
    public Uzivatel findUzivatelById(int id) {
        return manager.find(Uzivatel.class, id);
    }

    @Override
    public Uzivatel findUzivatelByUzivatelskeJmeno(String uzivatelskeJmeno) {
        Query query = manager.createQuery("SELECT u FROM Uzivatel u WHERE u.uzivatelskeJmeno = :jmeno");
        query.setParameter("jmeno", uzivatelskeJmeno);
        return (Uzivatel) query.getSingleResult();
    }

    @Override
    public List<Uzivatel> findAll() {
        Query query = manager.createQuery("SELECT u FROM Uzivatel u");
        return query.getResultList();
    }

    @Override
    public boolean jeUzivatelskeJmenoVolne(String uzivatelskeJmeno) {
        return false;
    }

    @Override
    public boolean overHashHesla(String hash) {
        return false;
    }
}