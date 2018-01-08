package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Kategorie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class KategorieDaoImpl implements KategorieDao {
    @PersistenceContext
    EntityManager manager;

    @Override
    public Kategorie merge(Kategorie Kategorie) {
        return manager.merge(Kategorie);
    }

    @Override
    public void save(Kategorie Kategorie) {
        manager.persist(Kategorie);
    }

    @Override
    public void delete(Kategorie Kategorie) {
        manager.remove(Kategorie);
    }

    @Override
    public Kategorie findKategorieById(int id) {
        return manager.find(Kategorie.class, id);
    }

    @Override
    public List<Kategorie> findAll() {
        String query = "SELECT k FROM Kategorie k";
        return manager.createQuery(query).getResultList();
    }
}
