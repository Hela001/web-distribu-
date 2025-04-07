package com.ms.projet.msprojethela;

import com.ms.projet.msprojethela.Projet;
import com.ms.projet.msprojethela.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

    @Query("select c from Projet c where c.nom like :name")
    Page<Projet> projetByNom(@Param("name") String n, Pageable pageable);

    @Query("SELECT p FROM Projet p WHERE p.status = :status")
    Page<Projet> findByStatus(@Param("status") Status status, Pageable pageable);


    Page<Projet> findAll(Pageable pageable);



}
