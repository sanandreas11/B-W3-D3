package dao;

import entities.Persona;
import jakarta.persistence.EntityManager;

public class PersonaDAO {
    private final EntityManager em;

    public PersonaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Persona l) {
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
    }
}