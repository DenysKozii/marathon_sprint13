package com.softserve.sprint13.entity;

import com.softserve.sprint13.validation.StartBeforeEndDateValidation;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "sprint")
@StartBeforeEndDateValidation(message = "Start date should be before finish date.")
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
    @Column(name = "title", unique = true)
    private String title;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "sprint",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @ToString.Exclude
    private List<Task> tasks;

    @PreRemove
    public void checkTaskAssociationBeforeRemoval() {
        if (!this.tasks.isEmpty()) {
            throw new RuntimeException("Can't remove a sprint that has tasks.");
        }
    }
}
