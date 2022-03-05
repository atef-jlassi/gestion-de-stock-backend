package org.commerce.gestiondestock.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.commerce.gestiondestock.enums.TypeMvtStk;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvt_stock")
public class MvtStk extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @Column(name = "date_mvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Enumerated(EnumType.STRING)
    private TypeMvtStk typeMvt;

}
