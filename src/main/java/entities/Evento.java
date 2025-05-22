package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_evento")
@NamedQueries({
        @NamedQuery(name = "Evento.getEventiSoldOut", query = "SELECT e FROM Evento e WHERE SIZE(e.partecipazioni) = e.numeroMassimoPartecipanti")
})
public abstract class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private LocalDate data;

    @ManyToOne
    private Location location;

    private int numeroMassimoPartecipanti;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Partecipazione> partecipazioni;

    // Getters e setters
    public Long getId() { return id; }
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public int getNumeroMassimoPartecipanti() { return numeroMassimoPartecipanti; }
    public void setNumeroMassimoPartecipanti(int numero) { this.numeroMassimoPartecipanti = numero; }

    public List<Partecipazione> getPartecipazioni() { return partecipazioni; }
    public void setPartecipazioni(List<Partecipazione> partecipazioni) { this.partecipazioni = partecipazioni; }
}
