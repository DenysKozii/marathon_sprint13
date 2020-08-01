package com.softserve.sprint13.controller;


import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.service.MarathonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MarathonController {

  Logger logger = LoggerFactory.getLogger(MarathonController.class);

  @Autowired
  private MarathonService marathonService;

  @GetMapping({"/", "/marathons"})
  public String getAllMarathons(Model model) {
    logger.info("get all marathons");
    List<Marathon> marathons = marathonService.getAll();
    model.addAttribute("marathons", marathons);
    model.addAttribute("add", false);
    return "marathons";
  }

  @GetMapping("/marathons/edit/{id}")
  public String editMarathonForm(@PathVariable("id") Long id, Model model) {
    logger.info("edit marathon form");
    model.addAttribute("marathon", marathonService.getMarathonById(id));
    return "edit";
  }

  @GetMapping("/marathons/create")
  public String createMarathonForm(Model model) {
    return "create";
  }

  @PostMapping("/marathons/edit")
  public String editMarathon(@ModelAttribute("marathon") Marathon marathon) {
    logger.info("edit marathon");
    marathonService.createOrUpdateMarathon(marathon);
    return "redirect:/marathons";
  }


  @PostMapping("/marathons/create")
  public String createMarathons(@ModelAttribute("marathon") Marathon marathon) {
    logger.info("creat marathon");
    marathonService.createOrUpdateMarathon(marathon);
    return "redirect:/marathons";
  }

  @GetMapping("/marathons/delete/{id}")
  public String deleteMarathons(@PathVariable("id") Long id) {
    logger.info("delete marathon");
    marathonService.deleteMarathonById(id);
    return "redirect:/marathons";
  }
}
