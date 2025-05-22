package dao;

import entities.*;
import jakarta.persistence.EntityManager;
import java.util.List;

public class EventoDAO {

    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Evento e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public List<Concerto> getConcertiInStreaming(boolean streaming) {
        return em.createQuery("SELECT c FROM Concerto c WHERE c.inStreaming = :streaming", Concerto.class)
                .setParameter("streaming", streaming)
                .getResultList();
    }

    public List<Concerto> getConcertiPerGenere(enumerations.GenereConcerto genere) {
        return em.createQuery("SELECT c FROM Concerto c WHERE c.genere = :genere", Concerto.class)
                .setParameter("genere", genere)
                .getResultList();
    }

    public List<PartitaDiCalcio> getPartiteVinteInCasa() {
        return em.createQuery("SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraDiCasa", PartitaDiCalcio.class).getResultList();
    }

    public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
        return em.createQuery("SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraOspite", PartitaDiCalcio.class).getResultList();
    }

    public List<PartitaDiCalcio> getPartitePareggiate() {
        return em.createQuery("SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente IS NULL", PartitaDiCalcio.class).getResultList();
    }

    public List<GaraDiAtletica> getGarePerVincitore(Persona vincitore) {
        return em.createQuery("SELECT g FROM GaraDiAtletica g WHERE g.vincitore = :vincitore", GaraDiAtletica.class)
                .setParameter("vincitore", vincitore)
                .getResultList();
    }

    public List<GaraDiAtletica> getGarePerPartecipante(Persona partecipante) {
        return em.createQuery("SELECT g FROM GaraDiAtletica g JOIN g.atleti a WHERE a = :partecipante", GaraDiAtletica.class)
                .setParameter("partecipante", partecipante)
                .getResultList();
    }

    public List<Evento> getEventiSoldOut() {
        return em.createNamedQuery("Evento.getEventiSoldOut", Evento.class).getResultList();
    }
}