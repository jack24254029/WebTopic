package com.shunminchang.controller;

import com.shunminchang.model.NurseEntity;
import com.shunminchang.model.SiteEntity;
import com.shunminchang.model.TaskEntity;
import com.shunminchang.repository.NurseRepository;
import com.shunminchang.repository.SiteRepository;
import com.shunminchang.repository.TaskRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public String addNurse(ModelMap modelMap) {
        List<SiteEntity> siteEntities = siteRepository.findAll();
        modelMap.addAttribute("unSelectedSiteList", siteEntities);
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

    @PostMapping(value = "/nurse/addSiteOfNurse")
    public @ResponseBody
    ResponseEntity<String> addSiteOfNurse(@RequestBody String json) {
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        JSONObject jsonObject = new JSONObject(json);
        String nurseId = jsonObject.getString("nurseId");
        String nurseName = jsonObject.getString("nurseName");
        NurseEntity nurseEntity = new NurseEntity();
        nurseEntity.setId(nurseId);
        nurseEntity.setName(nurseName);
        nurseEntity.setCreateTime(createTime);
        nurseEntity.setUpdateTime(createTime);
        nurseRepository.saveAndFlush(nurseEntity);
        JSONArray selectedArray = jsonObject.getJSONArray("selectedSiteIds");
        for (int i = 0; i < selectedArray.length(); i++) {
            int siteId = selectedArray.getInt(i);
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setNurseId(nurseId);
            taskEntity.setSiteId(siteId);
            taskEntity.setCreateTime(createTime);
            taskRepository.saveAndFlush(taskEntity);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "/nurse/show/{id}", method = RequestMethod.GET)
    public String showNurse(@PathVariable("id") String id, ModelMap modelMap) {
        // 取得該護士的資料
        NurseEntity nurseEntity = nurseRepository.findById(id).get();
        modelMap.addAttribute("nurse", nurseEntity);
        List<SiteEntity> selectedSiteEntities = new ArrayList<>();
        List<SiteEntity> unselectedSiteEntities = siteRepository.findAll();
        // 先查詢有沒有已分配的站點
        List<TaskEntity> taskEntities = taskRepository.findAllByNurseId(id);
        // 如果有資料，繼續查詢站點名稱
        if (taskEntities.size() > 0) {
            for (TaskEntity taskEntity : taskEntities) {
                try {
                    SiteEntity siteEntity = siteRepository.findById(taskEntity.getSiteId()).get();
                    selectedSiteEntities.add(siteEntity);
                } catch (NoSuchElementException ex) {
                    // do nothing;
                }
            }
            // 比對沒被分配到的站點是哪些
            for (int i = 0; i < selectedSiteEntities.size(); i++) {
                if (unselectedSiteEntities.contains(selectedSiteEntities.get(i))) {
                    unselectedSiteEntities.remove(selectedSiteEntities.get(i));
                }
            }
        }
        modelMap.addAttribute("selectedSiteList", selectedSiteEntities);
        modelMap.addAttribute("unSelectedSiteList", unselectedSiteEntities);
        return "nurse/nurseDetail";
    }

    @RequestMapping(value = "/nurse/updateNurseP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("nurse") NurseEntity nurseEntity) {
        nurseRepository.updateUser(nurseEntity.getName(), new Timestamp(System.currentTimeMillis()), nurseEntity.getId());
        nurseRepository.flush();
        return "redirect:/nurse/nurseList";
    }

    @PostMapping(value = "/nurse/updateSiteOfNurse")
    public @ResponseBody
    ResponseEntity<String> updateSiteOfNurse(@RequestBody String json) {
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        JSONObject jsonObject = new JSONObject(json);
        String nurseId = jsonObject.getString("nurseId");
        JSONArray unSelectedArray = jsonObject.getJSONArray("unSelectedSiteIds");
        JSONArray selectedArray = jsonObject.getJSONArray("selectedSiteIds");
        for (int i = 0; i < unSelectedArray.length(); i++) {
            int siteId = unSelectedArray.getInt(i);
            TaskEntity taskEntity = checkTaskEntity(nurseId, siteId);
            if (taskEntity != null) {
                taskRepository.delete(taskEntity);
            }
        }
        for (int i = 0; i < selectedArray.length(); i++) {
            int siteId = selectedArray.getInt(i);
            TaskEntity taskEntity = checkTaskEntity(nurseId, siteId);
            if (taskEntity == null) {
                taskEntity = new TaskEntity();
                taskEntity.setSiteId(siteId);
                taskEntity.setNurseId(nurseId);
                taskEntity.setCreateTime(createTime);
                taskRepository.saveAndFlush(taskEntity);
            }
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    private TaskEntity checkTaskEntity(String nurseId, int siteId) {
        TaskEntity taskEntity = taskRepository.findAllByNurseIdAndSiteId(nurseId, siteId);
        return taskEntity;
    }

    @RequestMapping(value = "/nurse/delete/{id}", method = RequestMethod.GET)
    public String deleteNurse(@PathVariable("id") String id) {
        NurseEntity nurseEntity = nurseRepository.getOne(id);
        nurseRepository.delete(nurseEntity);
        return "redirect:/nurse/nurseList";
    }
}
