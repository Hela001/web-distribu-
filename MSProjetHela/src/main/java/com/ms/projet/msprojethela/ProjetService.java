package com.ms.projet.msprojethela;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjetService implements IProjetService {

    private final ProjetRepository projetRepository;

    // Récupérer tous les projets sans pagination
    @Override
    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    // Récupérer un projet par ID
    public Optional<Projet> getProjetById(Long id) {
        return projetRepository.findById(id);
    }

    // Ajouter un projet
    @Override
    @Transactional
    public Projet addProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    // Mettre à jour un projet
    @Override
    @Transactional
    public Projet updateProjet(Long id, Projet projet) {
        return projetRepository.findById(id).map(existingProjet -> {
            projet.setIdProjet(id);  // Assurer que l'ID reste celui du projet existant
            return projetRepository.save(projet);
        }).orElseThrow(() -> new EntityNotFoundException("Projet non trouvé avec l'ID " + id));
    }

    // Supprimer un projet
    @Override
    public String deleteProjet(Long id) {
        if (projetRepository.existsById(id)) {
            projetRepository.deleteById(id);
            return "Projet supprimé avec succès";
        }
        return "Projet non trouvé";
    }

    public Page<Projet> getProjetsByStatus(Status status, Pageable pageable) {
        return projetRepository.findByStatus(status, pageable);
    }

    // Ajouter la prise en charge de la pagination
    public Page<Projet> getAllProjets(Pageable pageable) {
        return projetRepository.findAll(pageable);
    }
    public byte[] generateProjetPdf(Long projetId) throws IOException {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new EntityNotFoundException("Projet non trouvé avec l'ID " + projetId));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Ajout des détails du projet
        document.add(new Paragraph("Détails du Projet"));
        document.add(new Paragraph("Nom: " + projet.getNom()));  // Changement de getName() -> getNom()
        document.add(new Paragraph("Description: " + projet.getDescription()));
        document.add(new Paragraph("Status: " + projet.getStatus()));
        document.add(new Paragraph("Date de début: " + projet.getDateDebut()));
        document.add(new Paragraph("Date de fin prévue: " + projet.getDateFinPrevue()));

        document.close();
        return out.toByteArray();
    }


}
