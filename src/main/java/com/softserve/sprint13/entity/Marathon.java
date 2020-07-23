package com.softserve.sprint13.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "marathon")
public class Marathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "title")
    private String title;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Marathon{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
