package com.catchzombie.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ashsish on 6/2/17.
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "ROLE_PERMISSIONS")
public class Permission extends AbstractPersistable<Long>{

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String title;

    @NotEmpty
    @Column(nullable = false)
    private String description;
}
