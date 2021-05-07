package com.dolap.product.entity.base;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate = Date.from(Instant.now());

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
