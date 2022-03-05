package org.commerce.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.commerce.gestiondestock.models.Roles;

import java.util.List;

@Data
@Builder
public class RolesDto {

    private Integer id;

    private String roleName;

    @JsonIgnore
    private UtilisateurDto utilisateur;


    public static RolesDto fromEntity(Roles roles) {
        if (roles==null) {
            return null;
        }

        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();

    }

    public static Roles toEntity(RolesDto rolesDto) {

        if (rolesDto==null) {
            return null;
        }

        Roles roles = new Roles();

        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        roles.setUtilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateur()));
        return roles;
    }
}
