import enumerations.Sesso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import dao.PersonaDAO;
import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;

import entities.Persona;
import entities.Evento;
import entities.Location;
import entities.Partecipazione;
import entities.Partecipazione.Stato;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProvaJPA");
        EntityManager em = emf.createEntityManager();

        PersonaDAO personaDAO = new PersonaDAO(em);
        EventoDAO eventoDAO = new EventoDAO(em);
        LocationDAO locationDAO = new LocationDAO(em);
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);

        // Crea oggetti
        Location location = new Location();
        location.setNome("Palazzo Congressi");
        location.setCitta("Milano");

        Evento evento = new Evento();
        evento.setTitolo("Tech Talk");
        evento.setData(LocalDate.of(2025, 6, 10));
        evento.setLocation(location);

        Persona persona = new Persona();
        persona.setNome("Luca");
        persona.setCognome("Bianchi");
        persona.setEmail("luca@email.com");
        persona.setDataNascita(LocalDate.of(1990, 5, 15));
        persona.setSesso(Sesso.M);

        Partecipazione partecipazione = new Partecipazione();
        partecipazione.setEvento(evento);
        partecipazione.setPersona(persona);
        partecipazione.setStato(Stato.CONFERMATA);

        evento.setPartecipazioni(new ArrayList<>());
        persona.setPartecipazioni(new ArrayList<>());

        evento.getPartecipazioni().add(partecipazione);
        persona.getPartecipazioni().add(partecipazione);
        
        try {
            em.getTransaction().begin();

            locationDAO.salva(location);
            eventoDAO.salva(evento);
            personaDAO.salva(persona);
            partecipazioneDAO.salva(partecipazione);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.getMessage();
        } finally {
            em.close();
            emf.close();
        }
    }
}