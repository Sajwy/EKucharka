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
        if(manager.contains(kategorie)) {
            manager.remove(kategorie);
        } else {
            Kategorie k = manager.getReference(Kategorie.class, kategorie.getId());
            manager.remove(k);
        }
    }

    @Override
    public Kategorie findKategorieById(int id) {
        return manager.find(Kategorie.class, id);
    }

    @Override
    public List<Kategorie> findAll() {
        Query query = manager.createQuery("SELECT k FROM Kategorie k ORDER BY k.id");
        return query.getResultList();
    }

    @Override
    public boolean jeIDvDB(int id) {
        try {
            Query query = manager.createQuery("SELECT k FROM Kategorie k WHERE k.id = :id");
            query.setParameter("id", id);
            Kategorie kategorie = (Kategorie) query.getSingleResult();

            if(kategorie != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean lzeNazevPouzit(String nazev) {
        try {
            Query query = manager.createQuery("SELECT k FROM Kategorie k WHERE LOWER(k.nazev) = :nazev");
            query.setParameter("nazev", nazev.toLowerCase());
            Kategorie k = (Kategorie) query.getSingleResult();

            if(k != null)
                return false;
            else
                return true;
        } catch (Exception e) {
            return true;
        }
    }

}