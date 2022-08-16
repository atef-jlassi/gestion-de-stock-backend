package org.commerce.gestiondestock.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private Instant lastUpdateDate;

    //    @PrePersist
    //    void prePersist() {
    //        creationDate = Instant.now();
    //        lastUpdateDate = Instant.now();
    //    }
    //
    //
    //    @PreUpdate
    //    void preUpdate() {
    //        lastUpdateDate = Instant.now();
    //    }
}
