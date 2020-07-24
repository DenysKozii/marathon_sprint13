package com.softserve.sprint13.repository;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<Progress,Long> {
}
