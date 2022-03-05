package org.commerce.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private Instant dateDeNaissance;

    private String email;

    private String motDePasse;

    private String photo;

    private AdresseDto adresse;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;
}
