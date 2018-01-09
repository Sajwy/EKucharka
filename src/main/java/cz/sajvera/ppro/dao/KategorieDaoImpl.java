package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Kategorie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class KategorieDaoImpl implements KategorieDao {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Kategorie merge(Kategorie kategorie) {
        return manager.merge(kategorie);
    }

    @Override
    public void save(Kategorie kategorie) {
        manager.persist(kategorie);
    }

    @Override
    public void delete(Kategorie kategorie) {
        manager.remove(kategorie);
    }

    @Override
    public Kategorie findKategorieById(int id) {
        return manager.find(Kategorie.class, id);
    }

    @Override
    public List<Kategorie> findAll() {
        Query query = manager.createQuery("SELECT k FROM Kategorie k");
        return query.getResultList();
    }
}