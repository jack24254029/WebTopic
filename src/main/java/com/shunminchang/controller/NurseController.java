package com.shunminchang.controller;

import com.shunminchang.model.NurseEntity;
import com.shunminchang.model.SiteEntity;
import com.shunminchang.model.TaskEntity;
import com.shunminchang.repository.NurseRepository;
import com.shunminchang.repository.SiteRepository;
import com.shunminchang.repository.TaskRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NurseController {
    @Autowired
    NurseRepository nurseRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    SiteRepository siteRepository;

    @RequestMapping(value = "/nurse/nurseList", method = RequestMethod.GET)
    public String getNurse(ModelMap modelMap) {
        List<NurseEntity> nurseEntityList = nurseRepository.findAll();
        modelMap.addAttribute("nurseList", nurseEntityList);
        return "nurse/nurseList";
    }

    @RequestMapping(value = "/nurse/addNurse", method = RequestMethod.GET)
    public String addNurse() {
        return "nurse/addNurse";
    }

    @RequestMapping(value = "/nurse/addNurseP", method = RequestMethod.POST)
    public String addNurseP(@ModelAttribute("nurse") NurseEntity nurseEntity) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        nurseEntity.setCreateTime(timestamp);
        nurseEntity.setUpdateTime(timestamp);
        nurseRepository.saveAndFlush(nurseEntity);
        return "redirect:/nurse/nurseList";
    }

    @RequestMapping(value = "/nurse/show/{id}", method = RequestMethod.GET)
    public String showNurse(@PathVariable("id") String id, ModelMap modelMap) {
        NurseEntity nurseEntity = nurseRepository.findById(id).get();
        modelMap.addAttribute("nurse", nurseEntity);
        List<TaskEntity> unSelectedTaskEntities = taskRepository.findAllByNurseIdNotQuery(id);
        List<SiteEntity> unSelectedSiteEntities = new ArrayList<>();
        for (TaskEntity taskEntity : unSelectedTaskEntities) {
            SiteEntity siteEntity = siteRepository.findById(taskEntity.getSiteId()).get();
            unSelectedSiteEntities.add(siteEntity);
        }
        modelMap.addAttribute("unSelectedSiteList", unSelectedSiteEntities);

        List<TaskEntity> selectedTaskEntities = taskRepository.findAllByNurseId(id);
        List<SiteEntity> selectedSiteEntities = new ArrayList<>();
        for (TaskEntity taskEntity : selectedTaskEntities) {
            SiteEntity siteEntity = siteRepository.findById(taskEntity.getSiteId()).get();
            selectedSiteEntities.add(siteEntity);
        }
        modelMap.addAttribute("selectedSiteList", selectedSiteEntities);
        return "nurse/nurseDetail";
    }

    @RequestMapping(value = "/nurse/updateNurseP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("nurse") NurseEntity nurseEntity, @ModelAttribute("selectedSiteList") SiteEntity siteEntity) {
        nurseRepository.updateUser(nurseEntity.getName(), new Timestamp(System.currentTimeMillis()), nurseEntity.getId());
        nurseRepository.flush();
        return "redirect:/nurse/nurseList";
    }

    @RequestMapping(value = "/nurse/delete/{id}", method = RequestMethod.GET)
    public String deleteNurse(@PathVariable("id") String id) {
        NurseEntity nurseEntity = nurseRepository.getOne(id);
        nurseRepository.delete(nurseEntity);
        return "redirect:/nurse/nurseList";
    }
}
