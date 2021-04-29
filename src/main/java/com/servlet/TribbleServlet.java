package com.servlet;

import com.controller.TribbleController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Tribble;
import com.service.TribbleService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet(urlPatterns={"*.tribble"})
public class TribbleServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TribbleService tribbleService = new TribbleService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TribbleController tc = new TribbleController();

        switch (req.getRequestURI()){
            case "/tribble/getAll":
                tc.getAll(req,resp);
                break;
            case "/tribble/byId":
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
        res = tribbleService.insertNew(objectMapper.readValue(jsonString, Tribble.class));

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


        String res = tribbleService.delete(objectMapper.readValue(jsonString, Tribble.class));
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

        String res = tribbleService.update(objectMapper.readValue(jsonString, Tribble.class));
        resp.getWriter().append(res);
        if(!res.equalsIgnoreCase("success")){
            resp.setStatus(400);
        }else{
            resp.setStatus(200);
        }
    }
}
