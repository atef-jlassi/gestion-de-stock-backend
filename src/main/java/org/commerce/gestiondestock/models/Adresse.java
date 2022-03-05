package org.commerce.gestiondestock.models;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class Adresse implements Serializable {

    @Column(name = "adresse_1")
    private String adresse1;

    @Column(name = "adresse_2")
    private String adresse2;

    @Column(name = "ville")
    private String ville;

    @Column(name = "code_postale")
    private String codePostale;

    @Column(name = "pays")
    private String pays;

}
