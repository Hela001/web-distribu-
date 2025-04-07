package com.ms.projet.msprojethela;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableDiscoveryClient
public class MsProjetHelaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProjetHelaApplication.class, args);
    }

    private final ProjetRepository projetRepository;

    @Autowired
    public MsProjetHelaApplication(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @Bean
    ApplicationRunner init() {
        return args -> {
            // Sauvegarde des projets
            projetRepository.save(new Projet("Hôtel", "Ceci est un hôtel", Status.TODO));
            projetRepository.save(new Projet("Chambre", "Ceci est une chambre", Status.DOING));
            projetRepository.save(new Projet("Bâtiment", "Infrastructure", Status.DONE));
            // Affichage des projets
            projetRepository.findAll().forEach(System.out::println);
        };
    }
}
