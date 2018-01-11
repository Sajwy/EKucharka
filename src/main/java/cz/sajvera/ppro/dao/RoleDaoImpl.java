package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Role merge(Role role) {
        return manager.merge(role);
    }

    @Override
    public void save(Role role) {
        manager.persist(role);
    }

    @Override
    public void delete(Role role) {
        manager.remove(role);
    }

    @Override
    public Role findRoleById(int id) {
        return manager.find(Role.class, id);
    }

    @Override
    public Role findRoleByNazev(String nazev) {
        Query query = manager.createQuery("SELECT r FROM Role r WHERE r.nazev = :nazev");
        query.setParameter("nazev", nazev);
        return (Role) query.getSingleResult();
    }

    @Override
    public List<Role> findAll() {
        Query query = manager.createQuery("SELECT r FROM Role r");
        return query.getResultList();
    }
}