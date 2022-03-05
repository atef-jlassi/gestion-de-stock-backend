package org.commerce.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;
import org.commerce.gestiondestock.enums.TypeMvtStk;
import org.commerce.gestiondestock.models.MvtStk;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Integer id;

    private ArticleDto article;

    private Instant dateMvt;

    private BigDecimal quantite;

    private TypeMvtStk typeMvt;


    public static MvtStkDto fromEntity(MvtStk mvtStk) {
        if (mvtStk==null) {
            return null;
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .article(ArticleDto.fromEntity(mvtStk.getArticle()))
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .typeMvt(mvtStk.getTypeMvt())
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto) {
        if (mvtStkDto==null) {
            return null;
        }

        MvtStk mvtStk=new MvtStk();

        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setTypeMvt(mvtStkDto.getTypeMvt());

        return mvtStk;
    }
}
