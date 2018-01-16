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
        if(manager.contains(uzivatel)) {
            manager.remove(uzivatel);
        } else {
            Uzivatel u = manager.getReference(Uzivatel.class, uzivatel.getId());
            manager.remove(u);
        }
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
        Query query = manager.createQuery("SELECT u FROM Uzivatel u order by u.role.nazev, u.uzivatelskeJmeno");
        return query.getResultList();
    }

    @Override
    public List<Uzivatel> findAllExceptMe(Uzivatel uzivatel) {
        Query query = manager.createQuery("SELECT u FROM Uzivatel u WHERE u.uzivatelskeJmeno <> :jmeno order by u.role.nazev, u.uzivatelskeJmeno");
        query.setParameter("jmeno", uzivatel.getUzivatelskeJmeno());
        return query.getResultList();
    }

    @Override
    public boolean jeUzivatelskeJmenoVolne(String uzivatelskeJmeno) {
        try {
            Query query = manager.createQuery("SELECT u FROM Uzivatel u WHERE u.uzivatelskeJmeno = :jmeno");
            query.setParameter("jmeno", uzivatelskeJmeno);
            Uzivatel u = (Uzivatel) query.getSingleResult();

            if(u != null)
                return false;
            else
                return true;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean overUzivatele(String uzivatelskeJmeno, String hash) {
        try {
            Query query = manager.createQuery("SELECT u FROM Uzivatel u WHERE u.uzivatelskeJmeno = :jmeno AND u.heslo = :heslo");
            query.setParameter("jmeno", uzivatelskeJmeno);
            query.setParameter("heslo", hash);
            Uzivatel u = (Uzivatel) query.getSingleResult();

            if(u != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}