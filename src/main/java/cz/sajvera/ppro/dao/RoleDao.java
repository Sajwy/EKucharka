package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Role;

import java.util.List;

public interface RoleDao {

    public Role merge(Role role);

    public void save(Role role);

    public void delete(Role role);

    public Role findRoleById(int id);

    public Role findRoleByNazev(String nazev);

    public List<Role> findAll();
    
}