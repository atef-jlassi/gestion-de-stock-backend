package org.commerce.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;
import org.commerce.gestiondestock.models.Adresse;

@Builder
@Data
public class AdresseDto {

    private String adresse1;

    private String adresse2;

    private String ville;

    private String codePostale;

    private String pays;

    public static AdresseDto fromEntity(Adresse adresse) {
        if (adresse==null) {
            // TODO throw an exception
            return null;
        }

        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .ville(adresse.getVille())
                .codePostale(adresse.getCodePostale())
                .pays(adresse.getCodePostale())
                .build();
    }

    public static Adresse toEntity(AdresseDto adresseDto) {
        if (adresseDto==null) {
            // TODO throw an exception
            return null;
        }

        Adresse adresse = new Adresse();
        adresse.setAdresse1(adresseDto.getAdresse1());
        adresse.setAdresse2(adresseDto.getAdresse2());
        adresse.setVille(adresseDto.getVille());
        adresse.setCodePostale(adresseDto.getCodePostale());
        adresse.setPays(adresseDto.getPays());

        return adresse;
    }
}
