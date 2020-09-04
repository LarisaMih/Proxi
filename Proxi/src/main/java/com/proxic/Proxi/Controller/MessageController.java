package com.proxic.Proxi.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.proxic.Proxi.Entity.Mac;
import com.proxic.Proxi.Entity.Sport;
import com.proxic.Proxi.Repositories.Hran;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


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

    @GetMapping("/olimp1/{id}")
    public Object sportName(@PathVariable("id") String id) throws InterruptedException, JsonProcessingException {
        RestTemplate restTemplateName = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        Hran response = restTemplateName.getForObject("https://www.olimp.bet/apiru/live/sport?id=" + id, Hran.class
        );
        String allResponse = objectMapper.writeValueAsString(response);
        String nameSport = JsonPath.read(allResponse, "sport.name");
        Sport sport = new Sport();
        sport.setSport(nameSport);
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

        Mac mac=new Mac();
        mac.setMac(sb.toString());
        return mac;
    }

   // public static List<Map<String, String>> messages = new ArrayList<>();// хранит сообщение о пустой таблице
    @GetMapping(value = "mac2", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Mac> Macc()  {

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
        Mac mac=new Mac();
        mac.setMac(sb.toString());

        List<Mac> list = new  LinkedList<>();
        list.add(mac);
        return list;
    }

    @GetMapping(value = "mac3", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> Mac3()  {

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

        List<String> list = new  LinkedList<>();
        list.add(resp);
        return list;
    }


}
