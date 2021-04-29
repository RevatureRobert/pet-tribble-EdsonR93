package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Lab;
import com.service.LabService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class LabController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private LabService labService = new LabService();

    public LabController(){
    }

    public LabController(LabService ls){
        this.labService=ls;
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();
        System.out.println(jsonString);
        String json = objectMapper.writeValueAsString(labService.getAll());
        resp.getWriter().append(json);
        resp.setContentType("application/json");
    }

    public void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        BufferedReader reader = req.getReader();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();
        Lab lab = objectMapper.readValue(jsonString, Lab.class);
        int labId = lab.getLabId();
        String json = objectMapper.writeValueAsString(labService.getById(labId));
        resp.getWriter().append(json);
        resp.setContentType("application/json");
    }
}
