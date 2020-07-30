package com.softserve.sprint13.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {

    public enum Role {
        MENTOR, TRAINEE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
//    @Column(name = "email", unique = true)
    @Column(name = "email")
    @Pattern(regexp=".+@.+\\..+", message = "Please provide a valid email address")
    @EqualsAndHashCode.Include
    private String email;

    @NotBlank(message = "First name cannot be empty")
    @Column(name = "first_name")
    @EqualsAndHashCode.Include
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    @EqualsAndHashCode.Include
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @EqualsAndHashCode.Include
    private Role role;

    @NotNull
    @Column(name = "password")
    @Size(min = 6, max=20, message = "Password must be between 6 and 12 characters")
    @ToString.Exclude
    private String password;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "marathon_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "marathon_id"))
    @ToString.Exclude
    private List<Marathon> marathons;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "trainee",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Progress> progressList;

}
