package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Recept;
import cz.sajvera.ppro.model.Uzivatel;
import cz.sajvera.ppro.utils.ImageUtils;

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
        if(manager.contains(recept)) {
            ImageUtils.smazFotku(recept.getFotka());
            manager.remove(recept);
        } else {
            Recept r = manager.getReference(Recept.class, recept.getId());
            ImageUtils.smazFotku(r.getFotka());
            manager.remove(r);
        }
    }

    @Override
    public Recept findReceptById(int id) {
        return manager.find(Recept.class, id);
    }

    @Override
    public List<Recept> findAll() {
        Query query = manager.createQuery("SELECT r FROM Recept r ORDER BY r.kategorie.nazev, r.nazev");
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

    @Override
    public boolean maUzivatelOpravneniKReceptu(int id, Uzivatel uzivatel) {
        try {
            Query query = manager.createQuery("SELECT r FROM Recept r WHERE r.id = :id AND r.uzivatel = :uzivatel");
            query.setParameter("id", id);
            query.setParameter("uzivatel", uzivatel);
            Recept recept = (Recept) query.getSingleResult();

            if(recept != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Recept> findReceptsByUzivatel(Uzivatel uzivatel) {
        Query query = manager.createQuery("SELECT r FROM Recept r WHERE r.uzivatel = :uzivatel ORDER BY r.kategorie.nazev, r.datumPridani DESC ");
        query.setParameter("uzivatel", uzivatel);
        return query.getResultList();
    }
}