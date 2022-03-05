package org.commerce.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;
import org.commerce.gestiondestock.models.Utilisateur;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur==null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .email(utilisateur.getEmail())
                .motDePasse(utilisateur.getMotDePasse())
                .photo(utilisateur.getPhoto())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .roles(utilisateur.getRoles() != null ?
                        utilisateur.getRoles().stream().map(RolesDto::fromEntity).collect(Collectors.toList())
                        : null)
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {

        if (utilisateurDto==null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));

        return utilisateur;
    }
}
