package entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PartitaDiCalcio extends Evento {

    private String squadraDiCasa;
    private String squadraOspite;
    private String squadraVincente;
    private int golCasa;
    private int golOspite;

    // Getters, Setters, Costruttori

    public PartitaDiCalcio(Long id, String titolo, LocalDate data, Location location, List<Partecipazione> partecipazioni, String squadraDiCasa, String squadraOspite, String squadraVincente, int golCasa, int golOspite) {
        super(id, titolo, data, location, partecipazioni);
        this.squadraDiCasa = squadraDiCasa;
        this.squadraOspite = squadraOspite;
        this.squadraVincente = squadraVincente;
        this.golCasa = golCasa;
        this.golOspite = golOspite;
    }

    public String getSquadraDiCasa() {
        return squadraDiCasa;
    }

    public void setSquadraDiCasa(String squadraDiCasa) {
        this.squadraDiCasa = squadraDiCasa;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public void setSquadraOspite(String squadraOspite) {
        this.squadraOspite = squadraOspite;
    }

    public String getSquadraVincente() {
        return squadraVincente;
    }

    public void setSquadraVincente(String squadraVincente) {
        this.squadraVincente = squadraVincente;
    }

    public int getGolCasa() {
        return golCasa;
    }

    public void setGolCasa(int golCasa) {
        this.golCasa = golCasa;
    }

    public int getGolOspite() {
        return golOspite;
    }

    public void setGolOspite(int golOspite) {
        this.golOspite = golOspite;
    }
}