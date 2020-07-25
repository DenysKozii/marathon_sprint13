package com.softserve.sprint13.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @NotBlank(message = "Task title cannot be empty")
    @Column(name = "title", unique = true)
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "sprint_id")
    @ToString.Exclude
    private Sprint sprint;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "task",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @ToString.Exclude
    private List<Progress> progressList;

    @PreRemove
    public void checkProgressAssociationBeforeRemoval() {
        if (!this.progressList.isEmpty()) {
            throw new RuntimeException("Can't remove a task that has progress entities.");
        }
    }
}
