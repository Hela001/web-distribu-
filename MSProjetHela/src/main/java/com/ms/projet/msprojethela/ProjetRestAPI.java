package com.ms.projet.msprojethela;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projets")
public class ProjetRestAPI {

    private final ProjetService projetService;

    @Autowired
    public ProjetRestAPI(ProjetService projetService) {
        this.projetService = projetService;
    }

    private final String title = "Hello, I'm the Project Micro-Service";

    @GetMapping("/hello")
    public String getTitle() {
        return title;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Projet>> getAllProjets(Pageable pageable) {
        Page<Projet> projets = projetService.getAllProjets(pageable);
        return ResponseEntity.ok(projets);
    }


    // Récupérer un projet par ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projet> getProjetById(@PathVariable Long id) {
        return projetService.getProjetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Ajouter un projet
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Projet> addProjet(@RequestBody Projet projet) {
        Projet createdProjet = projetService.addProjet(projet);
        return new ResponseEntity<>(createdProjet, HttpStatus.CREATED);
    }

    // Mettre à jour un projet
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projet> updateProjet(@PathVariable Long id, @RequestBody Projet projet) {
        try {
            Projet updatedProjet = projetService.updateProjet(id, projet);
            return ResponseEntity.ok(updatedProjet);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Supprimer un projet
    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteProjet(@PathVariable Long id) {
        String response = projetService.deleteProjet(id);
        return response.equals("Projet supprimé avec succès") ?
                ResponseEntity.ok(response) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/status")
    public ResponseEntity<Page<Projet>> getProjets(
            @RequestParam(required = false) Status status,
            Pageable pageable) {
        Page<Projet> projets;

        if (status != null) {
            projets = projetService.getProjetsByStatus(status, pageable); // Méthode de filtrage par statut
        } else {
            projets = projetService.getAllProjets(pageable); // Méthode pour récupérer tous les projets
        }

        return ResponseEntity.ok(projets);
    }
    @GetMapping("/{id}/export-pdf")
    public ResponseEntity<byte[]> exportProjetToPdf(@PathVariable Long id) throws IOException, IOException {
        byte[] pdfContent = projetService.generateProjetPdf(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=projet_" + id + ".pdf")
                .body(pdfContent);
    }






}
