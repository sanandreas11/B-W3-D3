package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class GaraDiAtletica extends Evento {

    @ManyToMany
    private Set<Persona> atleti;

    @ManyToOne
    private Persona vincitore;

    // Getters, Setters, Costruttori

    public GaraDiAtletica(Long id, String titolo, LocalDate data, Location location, List<Partecipazione> partecipazioni, Set<Persona> atleti, Persona vincitore) {
        super(id, titolo, data, location, partecipazioni);
        this.atleti = atleti;
        this.vincitore = vincitore;
    }

    public Set<Persona> getAtleti() {
        return atleti;
    }

    public void setAtleti(Set<Persona> atleti) {
        this.atleti = atleti;
    }

    public Persona getVincitore() {
        return vincitore;
    }

    public void setVincitore(Persona vincitore) {
        this.vincitore = vincitore;
    }
}
