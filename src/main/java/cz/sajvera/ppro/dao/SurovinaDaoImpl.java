package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Surovina;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class SurovinaDaoImpl implements SurovinaDao {

    @PersistenceContext
    EntityManager manager;
    
    @Override
    public Surovina merge(Surovina surovina) {
        return manager.merge(surovina);
    }

    @Override
    public void save(Surovina surovina) {
        manager.persist(surovina);
    }

    @Override
    public void delete(Surovina surovina) {
        manager.remove(surovina);
    }

    @Override
    public Surovina findSurovinaById(int id) {
        return manager.find(Surovina.class, id);
    }

    @Override
    public Surovina findSurovinaByNazev(String nazev) {
        Query query = manager.createQuery("SELECT s FROM Surovina s WHERE s.nazev = :nazev");
        query.setParameter("nazev", nazev);
        return (Surovina) query.getSingleResult();
    }

    @Override
    public List<Surovina> findAll() {
        Query query = manager.createQuery("SELECT s FROM Surovina s");
        return query.getResultList();
    }
}