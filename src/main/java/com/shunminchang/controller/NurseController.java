package com.shunminchang.controller;

import com.shunminchang.model.NurseEntity;
import com.shunminchang.repository.NurseRepository;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
public class NurseController {
    @Autowired
    NurseRepository nurseRepository;

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
    public String showNurse(@PathVariable("id") Integer id, ModelMap modelMap) {
        NurseEntity nurseEntity = nurseRepository.findById(id).get();
        modelMap.addAttribute("nurse", nurseEntity);
        return "nurse/nurseDetail";
    }

    @RequestMapping(value = "/nurse/updateNurseP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("nurse") NurseEntity nurseEntity) {
        nurseRepository.updateUser(nurseEntity.getUid(), nurseEntity.getName(),
                new Timestamp(System.currentTimeMillis()), nurseEntity.getId());
        nurseRepository.flush();
        return "redirect:/nurse/nurseList";
    }

    @RequestMapping(value = "/nurse/delete/{id}", method = RequestMethod.GET)
    public String deleteNurse(@PathVariable("id") Integer id) {
        NurseEntity nurseEntity = nurseRepository.getOne(id);
        nurseRepository.delete(nurseEntity);
        return "redirect:/nurse/nurseList";
    }
}
