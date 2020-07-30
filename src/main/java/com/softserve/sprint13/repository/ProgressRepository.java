package com.softserve.sprint13.repository;

import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress,Long> {

    @Query(value = "select p.* from progress p \n" +
            "            left join users on p.trainee_id=users.id \n" +
            "            left join task on p.task_id=task.id \n" +
            "            left join sprint on task.sprint_id=sprint.id \n" +
            "            left join marathon on sprint.marathon_id=marathon.id \n" +
            "            where users.id=:userId and marathon.id=:marathonId",
            nativeQuery=true)
    List<Progress> allProgressByUserIdAndMarathonId(@Param("userId") Long userId, @Param("marathonId") Long marathonId);

    @Query(value = "select p.* from progress p \n" +
            "            left join users on p.trainee_id=users.id \n" +
            "            left join task on p.task_id=task.id \n" +
            "            left join sprint on task.sprint_id=sprint.id \n" +
            "            where users.id=:userId and sprint.id=:sprintId",
            nativeQuery=true)
    List<Progress> allProgressByUserIdAndSprintId(@Param("userId") Long userId,@Param("sprintId") Long sprintId);
//    Progress findById(Long id);
    Progress findByTraineeAndTask(User trainee, Task task);
}
