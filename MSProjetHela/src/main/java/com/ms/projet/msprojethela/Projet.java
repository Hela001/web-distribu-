package com.ms.projet.msprojethela;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idProjet;

    private String nom;
    private String description;

    @Enumerated(EnumType.STRING)
    Status status;

    // Ajouter l'annotation JsonFormat pour spécifier le format de la date
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinPrevue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinReelle;

    private BigDecimal budgetInitial;
    private BigDecimal budgetReel;

    private String adresse;
    private Double latitude;
    private Double longitude;
    private Boolean permisConstruction;

    // Constructeur personnalisé pour l'initialisation dans ApplicationRunner
    public Projet(String nom, String description, Status status) {
        this.nom = nom;
        this.description = description;
        this.status = status;
    }
}
