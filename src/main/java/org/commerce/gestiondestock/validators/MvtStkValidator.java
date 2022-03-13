package org.commerce.gestiondestock.validators;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.commerce.gestiondestock.dto.MvtStkDto;
import org.springframework.util.StringUtils;

public class MvtStkValidator {

  public static List<String> validate(MvtStkDto mvtStkDto) {
    List<String> errors = new ArrayList<>();
    if (mvtStkDto == null) {
      errors.add("Veuillez renseigner la date du mouvenent");
      errors.add("Veuillez renseigner la quantite du mouvenent");
      errors.add("Veuillez renseigner l'article");
      errors.add("Veuillez renseigner le type du mouvement");

      return errors;
    }
    if (mvtStkDto.getDateMvt() == null) {
      errors.add("Veuillez renseigner la date du mouvenent");
    }
    if (mvtStkDto.getQuantite() == null || mvtStkDto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
      errors.add("Veuillez renseigner la quantite du mouvenent");
    }
    if (mvtStkDto.getArticle() == null || mvtStkDto.getArticle().getId() == null) {
      errors.add("Veuillez renseigner l'article");
    }
    if (!StringUtils.hasLength(mvtStkDto.getTypeMvt().name())) {
      errors.add("Veuillez renseigner le type du mouvement");
    }

    return errors;
  }

}
