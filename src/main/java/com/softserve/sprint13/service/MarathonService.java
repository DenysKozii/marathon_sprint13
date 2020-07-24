package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;

import java.util.List;

public interface MarathonService {
    public List<Marathon> getAll();
    public Marathon getMarathonById(Long id);
    public Marathon createOrUpdate(Marathon marathon);
    public void deleteMarathonById(Long id);
}
