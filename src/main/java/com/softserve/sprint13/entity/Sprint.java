package com.softserve.sprint13.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "finish_date")
    private Date finishDate;

    @NotBlank(message = "Sprint title cannot be empty")
    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "sprint",
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Task> tasks;
}
