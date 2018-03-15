package com.shunminchang.controller;

import com.shunminchang.model.NurseEntity;
import com.shunminchang.model.SiteEntity;
import com.shunminchang.model.TaskEntity;
import com.shunminchang.repository.NurseRepository;
import com.shunminchang.repository.SiteRepository;
import com.shunminchang.repository.TaskRepository;
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
public class SiteController {
    @Autowired
    SiteRepository siteRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    NurseRepository nurseRepository;

    @RequestMapping(value = "/site/siteList", method = RequestMethod.GET)
    public String getSite(ModelMap modelMap) {
        // 取得站點資料
        List<SiteEntity> siteEntities = siteRepository.findAll();
        modelMap.addAttribute("siteList", siteEntities);
        return "site/siteList";
    }

    @RequestMapping(value = "/site/addSite", method = RequestMethod.GET)
    public String addSite() {
        return "site/addSite";
    }

    @RequestMapping(value = "/site/addSiteP", method = RequestMethod.POST)
    public String addSiteP(@ModelAttribute("site") SiteEntity siteEntity) {
        // 新增站點資料
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        siteEntity.setCreateTime(timestamp);
        siteEntity.setUpdateTime(timestamp);
        siteRepository.saveAndFlush(siteEntity);
        return "redirect:/site/siteList";
    }

    @RequestMapping(value = "/site/show/{id}", method = RequestMethod.GET)
    public String showSite(@PathVariable("id") Integer id, ModelMap modelMap) {
        // 取得站點資料
        SiteEntity siteEntity = siteRepository.findById(id).get();
        modelMap.addAttribute("site", siteEntity);
        List<TaskEntity> taskEntities = taskRepository.findAllBySiteId(id);
        modelMap.addAttribute("taskList", taskEntities);
        List<NurseEntity> nurseEntities = new ArrayList<>();
        // 取得該站點有哪些護士加入
        for (TaskEntity taskEntity : taskEntities) {
            NurseEntity nurseEntity = nurseRepository.findById(taskEntity.getNurseId()).get();
            nurseEntities.add(nurseEntity);
        }
        modelMap.addAttribute("nurseList", nurseEntities);
        return "site/siteDetail";
    }

    @RequestMapping(value = "/site/updateSiteP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("site") SiteEntity siteEntity) {
        // 更新站點資料
        siteRepository.updateSite(siteEntity.getName(),
                new Timestamp(System.currentTimeMillis()), siteEntity.getId());
        siteRepository.flush();
        return "redirect:/site/siteList";
    }

    @RequestMapping(value = "/site/delete/{id}", method = RequestMethod.GET)
    public String deleteSite(@PathVariable("id") Integer id) {
        // 刪除站點資料
        SiteEntity siteEntity = siteRepository.getOne(id);
        siteRepository.delete(siteEntity);
        return "redirect:/site/siteList";
    }
}
