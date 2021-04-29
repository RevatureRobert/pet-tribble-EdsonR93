package com.servlet;

import com.controller.TribbleController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Lab;
import com.service.LabService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns={"*.lab"})
public class LabServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final LabService labService = new LabService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TribbleController tc = new TribbleController();

        switch (req.getRequestURI()){
            case "/lab/getAll":
                tc.getAll(req,resp);
                break;
            case "/lab/byId":
                tc.getById(req, resp);
                break;
            default:
                System.out.println("not an expected URL");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();
        String res = null;
        res = labService.insertNew(objectMapper.readValue(jsonString, Lab.class));
        resp.getWriter().append(res);
        resp.setContentType("application/json");
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();

        String res = labService.delete(objectMapper.readValue(jsonString, Lab.class));
        resp.getWriter().append(res);
        if(!res.equalsIgnoreCase("success")){
            resp.setStatus(400);
        }else{
            resp.setStatus(200);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();
        String res = labService.update(objectMapper.readValue(jsonString, Lab.class));
        resp.getWriter().append(res);
        if(!res.equalsIgnoreCase("success")){
            resp.setStatus(400);
        }else{
            resp.setStatus(200);
        }
    }
}
