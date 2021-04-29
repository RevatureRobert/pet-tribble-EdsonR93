package com.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Tribble;
import com.service.TribbleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class TribbleController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private TribbleService ts = new TribbleService();

    public TribbleController(){
    }

    public TribbleController(TribbleService ts){
        this.ts=ts;
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
        String json = objectMapper.writeValueAsString(ts.getAll());
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
        Tribble tribble = objectMapper.readValue(jsonString, Tribble.class);
        int tribbleId = tribble.getTribbleId();
        String json = objectMapper.writeValueAsString(ts.getById(tribbleId));
        resp.getWriter().append(json);
        resp.setContentType("application/json");
    }

}
