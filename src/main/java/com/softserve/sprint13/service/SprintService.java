package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Sprint;

public interface SprintService {
    public Sprint getSprintByMarathonId(Sprint sprint, Marathon marathon);
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
    public boolean updateSprint(Sprint sprint);
    public Sprint getSprintById(Long id);
}
