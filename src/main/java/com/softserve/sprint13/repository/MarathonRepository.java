package com.softserve.sprint13.repository;

import com.softserve.sprint13.entity.Marathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarathonRepository extends JpaRepository<Marathon,Long> {

}
