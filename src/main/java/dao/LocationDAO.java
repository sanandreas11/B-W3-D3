package dao;

import entities.Location;
import jakarta.persistence.EntityManager;

public class LocationDAO {
    private final EntityManager em;

    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Location l) {
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
    }
}