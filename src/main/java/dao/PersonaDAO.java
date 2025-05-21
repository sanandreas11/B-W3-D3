package dao;

import entities.Persona;
import jakarta.persistence.EntityManager;

public class PersonaDAO {
    private EntityManager em;

    public PersonaDAO(EntityManager em) {
        this.em = em;
    }

    public void salva(Persona persona) {
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
    }

    public Persona trova(Long id) {
        return em.find(Persona.class, id);
    }
}
