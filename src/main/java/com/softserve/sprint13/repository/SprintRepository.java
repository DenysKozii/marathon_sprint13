package com.softserve.sprint13.repository;

import com.softserve.sprint13.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {

}
