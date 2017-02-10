package com.catchzombie.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ashsish on 7/2/17.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_CATEGORIES")
public class UserCategories extends AbstractPersistable<Long> {

    @NotEmpty
    @Column(nullable = false,unique = true)
    private String categoryName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MAPPING_USER_CATEGORIES_USER",joinColumns = @JoinColumn(name = "category_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

}
