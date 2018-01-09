package cz.sajvera.ppro.dao;

import cz.sajvera.ppro.model.Komentar;

public interface KomentarDao {

    public void save(Komentar komentar);

    public void delete(Komentar komentar);

}