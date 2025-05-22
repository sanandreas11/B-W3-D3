import dao.*;
import entities.*;
import enumerations.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventiPU");
        EntityManager em = emf.createEntityManager();

        // Inizializzo i DAO
        LocationDAO locationdao = new LocationDAO(em);
        PersonaDAO personadao = new PersonaDAO(em);
        EventoDAO eventodao = new EventoDAO(em);
        PartecipazioneDAO partecipazionedao = new PartecipazioneDAO(em);

        // 1. Creo e salvo una location
        Location location = new Location();
        location.setNome("Auditorium Parco della Musica");
        location.setCitta("Roma");
        locationdao.save(location);

        // 2. Creo e salvo una persona
        Persona persona = new Persona();
        persona.setNome("Giulia");
        persona.setCognome("Rossi");
        persona.setEmail("giulia.rossi@email.com");
        persona.setDataNascita(LocalDate.of(1992, 3, 25));
        persona.setSesso(Sesso.F);
        persona.setPartecipazioni(new ArrayList<>());
        personadao.save(persona);

        // 3. Creo e salvo un concerto
        Concerto concerto = new Concerto();
        concerto.setTitolo("Concerto di Musica Classica");
        concerto.setData(LocalDate.of(2025, 7, 15));
        concerto.setNumeroMassimoPartecipanti(1); // piccolo per test sold-out
        concerto.setLocation(location);
        concerto.setGenere(GenereConcerto.CLASSICO);
        concerto.setInStreaming(true);
        eventodao.save(concerto);

        // 4. Creo e salvo una partecipazione
        Partecipazione partecipazione = new Partecipazione();
        partecipazione.setPersona(persona);
        partecipazione.setEvento(concerto);
        partecipazione.setStato(Partecipazione.Stato.CONFERMATA);
        partecipazionedao.save(partecipazione);

        // 5. Eseguo alcune query DAO

        System.out.println("\n-- Concerti in streaming --");
        List<Concerto> concertiStreaming = eventodao.getConcertiInStreaming(true);
        concertiStreaming.forEach(c -> System.out.println(c.getTitolo()));

        System.out.println("\n-- Concerti per genere CLASSICO --");
        List<Concerto> concertiClassici = eventodao.getConcertiPerGenere(GenereConcerto.CLASSICO);
        concertiClassici.forEach(c -> System.out.println(c.getTitolo()));

        System.out.println("\n-- Eventi sold-out --");
        List<Evento> eventiSoldOut = eventodao.getEventiSoldOut();
        eventiSoldOut.forEach(e -> System.out.println(e.getTitolo()));

        System.out.println("\n-- Partecipazioni da confermare per il concerto --");
        List<Partecipazione> daConfermare = partecipazionedao.getPartecipazioniDaConfermarePerEvento(concerto);
        daConfermare.forEach(p -> System.out.println(p.getPersona().getNome()));

        // Chiusura risorse
        em.close();
        emf.close();
    }
}