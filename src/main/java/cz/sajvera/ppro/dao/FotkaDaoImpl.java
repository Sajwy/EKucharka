package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Fotka;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FotkaDaoImpl implements FotkaDao {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Fotka merge(Fotka fotka) {
        return manager.merge(fotka);
    }

    @Override
    public void save(Fotka fotka) {
        manager.persist(fotka);
    }

    @Override
    public void delete(Fotka fotka) {
        manager.remove(fotka);
    }
}