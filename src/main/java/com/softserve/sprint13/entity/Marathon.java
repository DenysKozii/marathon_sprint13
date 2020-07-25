package com.softserve.sprint13.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "marathon")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Marathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Marathon title cannot be empty")
    @Column(name = "title", unique = true)
    @EqualsAndHashCode.Include
    private String title;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "marathon_user",
            joinColumns = @JoinColumn(name = "marathon_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "marathon_id")
    @ToString.Exclude
    private List<Sprint> sprints;

    @PreRemove
    public void checkSprintAssociationBeforeRemoval() {
        if (!this.sprints.isEmpty()) {
            throw new RuntimeException("Can't remove a marathon that has sprints.");
        }
    }
}