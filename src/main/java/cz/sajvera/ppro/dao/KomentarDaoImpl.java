package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Komentar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class KomentarDaoImpl implements KomentarDao {

    @PersistenceContext
    EntityManager manager;

    @Override
    public void save(Komentar komentar) {
        manager.persist(komentar);
    }

    @Override
    public void delete(Komentar komentar) {
        manager.remove(komentar);
    }
}