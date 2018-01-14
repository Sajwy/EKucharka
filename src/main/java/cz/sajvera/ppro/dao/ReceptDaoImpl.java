package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Recept;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ReceptDaoImpl implements ReceptDao {

    @PersistenceContext
    EntityManager manager;
    
    @Override
    public Recept merge(Recept recept) {
        return manager.merge(recept);
    }

    @Override
    public void save(Recept recept) {
        manager.persist(recept);
    }

    @Override
    public void delete(Recept recept) {
        manager.remove(recept);
    }

    @Override
    public Recept findReceptById(int id) {
        return manager.find(Recept.class, id);
    }

    @Override
    public List<Recept> findAll() {
        Query query = manager.createQuery("SELECT r FROM Recept r");
        return query.getResultList();
    }

    @Override
    public List<Recept> findReceptsByKategorieID(int id) {
        Query query = manager.createQuery("SELECT r FROM Recept r WHERE r.kategorie.id = :kategorie ORDER BY r.datumPridani DESC ");
        query.setParameter("kategorie", id);
        return query.getResultList();
    }

    @Override
    public boolean jeIDvDB(int id) {
        try {
            Query query = manager.createQuery("SELECT r FROM Recept r WHERE r.id = :id");
            query.setParameter("id", id);
            Recept recept = (Recept) query.getSingleResult();

            if(recept != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}