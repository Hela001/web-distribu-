package com.ms.projet.msprojethela;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProjetService {
    List<Projet> getAllProjets();
    public Optional<Projet> getProjetById(Long id);
    Projet addProjet(Projet projet);
    Projet updateProjet(Long id, Projet projet);
    String deleteProjet(Long id);
    public Page<Projet> getProjetsByStatus(Status status, Pageable pageable);    }
