package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.Record;
import com.example.springbootdemo.service.AuthService;
import com.example.springbootdemo.service.RecordService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RecordController {

    private final RecordService recordService;
    private final AuthService authService;

    @Autowired
    public RecordController(RecordService recordService, AuthService authService) {
        this.recordService = recordService;
        this.authService = authService;
    }

    @GetMapping("/records")
    public String findAll(Model model) {
        if (authService.isAuthorized()) {
            List<Record> records = recordService.findAll();
            model.addAttribute("records", records);
        }
        return authService.isAuthorized() ? "record-list" : "redirect:/auth";
    }

    @GetMapping("/record-create")
    public String createRecordForm(Record record) {
        return authService.isAuthorized() ? "record-create" : "redirect:/auth";
    }

    @PostMapping("/record-create")
    public String createRecord(Record record) {
        if (authService.isAuthorized()) {
            if ("".equals(record.getBudget())) {
                record.setBudget(null);
            } else {
                record.setBudget(record.getBudget());
            }
            recordService.saveRecord(record);
        }
        return authService.isAuthorized() ? "redirect:/records" : "redirect:/auth";
    }

    @GetMapping("record-delete/{id}")
    public String deleteRecord(@PathVariable("id") Long id) {
        if (authService.isAuthorized()) {
            recordService.deleteById(id);
        }
        return authService.isAuthorized() ? "redirect:/records" : "redirect:/auth";
    }

    @GetMapping("/record-update/{id}")
    public String updateRecordForm(@PathVariable("id") Long id, Model model) {
        Record record = recordService.findById(id);
        model.addAttribute("record", record);
        return authService.isAuthorized() ? "record-update" : "redirect:/auth";
    }

    @PostMapping("/record-update")
    public String updateRecord(Record record) {
        if (authService.isAuthorized()) {
            if ("".equals(record.getBudget())) {
                record.setBudget(null);
            }
            recordService.saveRecord(record);
        }
        return authService.isAuthorized() ? "redirect:/records" : "redirect:/auth";
    }

    @PostMapping("/api/record/create")
    public ResponseEntity<String> createApi(HttpServletRequest request) {
        Record record = new Record();
        try {
            record.setFirstName(request.getParameter("firstName"));
            record.setLastName(request.getParameter("lastName"));
            record.setPhoneNumber(request.getParameter("phoneNumber"));
            record.setPersonCount(Integer.parseInt(request.getParameter("personCount")));
            record.setPreferences(request.getParameter("preferences"));
            record.setDate(request.getParameter("date"));
            record.setTime(request.getParameter("time"));
            record.setEventType(request.getParameter("eventType"));
            record.setEmail(request.getParameter("email"));


            if ("".equals(request.getParameter("budget"))) {
                record.setBudget(null);
            } else {
                record.setBudget(request.getParameter("budget"));
            }

            recordService.saveRecord(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/get-records")
    public ResponseEntity<JsonNode> getRecords() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode response = objectMapper.createObjectNode();
        ArrayNode recordsArray = objectMapper.createArrayNode();
        List<Record> records = recordService.findAll();
        for (Record record : records) {
            ObjectNode recordJson = objectMapper.createObjectNode();
            recordJson.put("id", record.getId());
            recordJson.put("firstName", record.getFirstName());
            recordJson.put("lastName", record.getLastName());
            recordJson.put("phoneNumber", record.getPhoneNumber());
            recordJson.put("personCount", record.getPersonCount());
            recordJson.put("preferences", record.getPreferences());
            recordJson.put("date", record.getDate());
            recordJson.put("time", record.getTime());
            recordJson.put("eventType", record.getEventType());
            recordJson.put("email", record.getEmail());
            recordJson.put("budget", record.getBudget());
            recordsArray.add(recordJson);
        }
        response.set("records", recordsArray);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
