package entities;

import jakarta.persistence.*;
import enumerations.GenereConcerto;

@Entity
@DiscriminatorValue("Concerto")
public class Concerto extends Evento {

    @Enumerated(EnumType.STRING)
    private GenereConcerto genere;

    private boolean inStreaming;

    // Getters e setters
    public GenereConcerto getGenere() { return genere; }
    public void setGenere(GenereConcerto genere) { this.genere = genere; }

    public boolean isInStreaming() { return inStreaming; }
    public void setInStreaming(boolean inStreaming) { this.inStreaming = inStreaming; }
}