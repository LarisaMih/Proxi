package com.proxic.Proxi.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.proxic.Proxi.Entity.Mac;
import com.proxic.Proxi.Entity.Sport;
import com.proxic.Proxi.Repositories.Hran;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@RestController //определяет класс как Контроллер
@RequestMapping("/api/v1")
public class MessageController {

    @GetMapping("/olimp/{id}")
    public Object sportAll(@PathVariable("id") String id) throws InterruptedException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Hran response = restTemplate.getForObject("https://www.olimp.bet/apiru/live/sport?id=" + id, Hran.class
        );
        return response;
    }

    @GetMapping("/olimp2/{id}")
    public Object sportAll1(@PathVariable("id") String id) throws InterruptedException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Object response = restTemplate.getForObject("https://www.olimp.bet/apiru/live/sport?id=" + id, Object.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        String nameMatch1 = JsonPath.read(response, "events[1].name");
        return nameMatch1;
    }

    public static List list = new ArrayList();

    @GetMapping("/olimp1/{id}")
    public Object sportName(@PathVariable("id") String id) throws InterruptedException, JsonProcessingException {
        RestTemplate restTemplateName = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String response = restTemplateName.getForObject("https://www.olimp.bet/apiru/live/sport?id=" + id, String.class);
        // String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);


        Sport sport = new Sport();
        Gson gson = new Gson();
        // String jsonString = gson.toJson(sport);
        // System.out.println("json " + jsonString);
        // String allResponse = objectMapper.writeValueAsString(response);
        String allResponse = response;


        int i = 0;
        while (true)
            try {
                String nameMatch1 = JsonPath.read(response, "events[" + i + "].name");
                list.add(nameMatch1);
                i++;
            } catch (PathNotFoundException e) {
                break;
            }
        return list;
    }

    @GetMapping("/olimp4/{id}")
    public Object sportNameObj(@PathVariable("id") String id) throws InterruptedException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String t = "{\"sport\":\"Футбол\",\"match\":\"Нью Таун Иглс - Хобарт Юнайтед\"}";
        Sport sport1 = objectMapper.readValue(t, Sport.class);
        String f = sport1.getSport();

        return f;
    }

    @GetMapping("/olimp3/{id}")
    public Object sportName3(@PathVariable("id") String id) throws InterruptedException, JsonProcessingException {
        RestTemplate restTemplateName = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String response = restTemplateName.getForObject("https://www.olimp.bet/apiru/live/sport?id=" + id, String.class);
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);

        System.out.println("json1 " + jsonString);
        System.out.println("js " + response);
        Sport sport = new Sport();
        Gson gson = new Gson();
        // String jsonString = gson.toJson(sport);
        // System.out.println("json " + jsonString);
        // String allResponse = objectMapper.writeValueAsString(response);
        String allResponse = response;
        String nameSport = JsonPath.read(allResponse, "sport.name");
        String nameMatch = JsonPath.read(allResponse, "events[0].name");

        sport.setSport(nameSport);
        // Sport match=new Sport();
        sport.setMatch(nameMatch);
        String t = "{\"sport\":\"Футбол\",\"match\":\"Нью Таун Иглс - Хобарт Юнайтед\"}";
        Sport sport1 = objectMapper.readValue(t, Sport.class);
        String f = sport1.getSport();
       /* String jsonString = gson.toJson(sport);
        System.out.println("json " + jsonString);*/


        return sport;
    }


    public static int GenerateNum(int from, int to) {
        int num = from + (int) (Math.random() * to);
        return num;
    }

    @GetMapping(value = "mac", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Mac() throws IOException {

        String[] ABC = {"A", "B", "C", "D", "E", "F"};
        String[] MAC = new String[6];

        for (int i = 0; i <= 5; i++) {
            MAC[i] = ABC[GenerateNum(0, 6)] + GenerateNum(0, 10);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAC.length; ++i) {
            if (i == MAC.length - 1) {
                sb.append(MAC[i]);
            } else {
                sb.append(MAC[i] + ":");
            }
        }
        String resp = "{\"mac\":\"" + sb.toString() + "\"}";
        return resp;
    }

    @PostMapping(value = "mac5", produces = MediaType.APPLICATION_JSON_VALUE)
    public String Mac5() throws IOException {

        String[] ABC = {"A", "B", "C", "D", "E", "F"};
        String[] MAC = new String[6];

        for (int i = 0; i <= 5; i++) {
            MAC[i] = ABC[GenerateNum(0, 6)] + GenerateNum(0, 10);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAC.length; ++i) {
            if (i == MAC.length - 1) {
                sb.append(MAC[i]);
            } else {
                sb.append(MAC[i] + ":");
            }
        }
        String resp = "{\"mac\":\"" + sb.toString() + "\"}";
        return resp;
    }


    @GetMapping(value = "mac1", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mac Mac1() throws IOException {

        String[] ABC = {"A", "B", "C", "D", "E", "F"};
        String[] MAC = new String[6];

        for (int i = 0; i <= 5; i++) {
            MAC[i] = ABC[GenerateNum(0, 6)] + GenerateNum(0, 10);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAC.length; ++i) {
            if (i == MAC.length - 1) {
                sb.append(MAC[i]);
            } else {
                sb.append(MAC[i] + ":");
            }
        }

        Mac mac = new Mac();
        mac.setMac(sb.toString());
        return mac;
    }

    // public static List<Map<String, String>> messages = new ArrayList<>();// хранит сообщение о пустой таблице
    @GetMapping(value = "mac2", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Mac> Macc() {

        String[] ABC = {"A", "B", "C", "D", "E", "F"};
        String[] MAC1 = new String[6];

        for (int i = 0; i <= 5; i++) {
            MAC1[i] = ABC[GenerateNum(0, 6)] + GenerateNum(0, 10);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAC1.length; ++i) {
            if (i == MAC1.length - 1) {
                sb.append(MAC1[i]);
            } else {
                sb.append(MAC1[i] + ":");
            }
        }
        Mac mac = new Mac();
        mac.setMac(sb.toString());

        List<Mac> list = new LinkedList<>();
        list.add(mac);
        return list;
    }

    @GetMapping(value = "mac3", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> Mac3() {

        String[] ABC = {"A", "B", "C", "D", "E", "F"};
        String[] MAC1 = new String[6];

        for (int i = 0; i <= 5; i++) {
            MAC1[i] = ABC[GenerateNum(0, 6)] + GenerateNum(0, 10);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAC1.length; ++i) {
            if (i == MAC1.length - 1) {
                sb.append(MAC1[i]);
            } else {
                sb.append(MAC1[i] + ":");
            }
        }
        String resp = "{\"mac\":\"" + sb.toString() + "\"}";

        List<String> list = new LinkedList<>();
        list.add(resp);
        return list;
    }


}
