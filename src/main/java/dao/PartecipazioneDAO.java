package dao;

import entities.Partecipazione;
import entities.Evento;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PartecipazioneDAO {
    private final EntityManager em;

    public PartecipazioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Partecipazione p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public List<Partecipazione> getPartecipazioniDaConfermarePerEvento(Evento evento) {
        return em.createNamedQuery("Partecipazione.getDaConfermarePerEvento", Partecipazione.class)
                .setParameter("evento", evento)
                .getResultList();
    }
}