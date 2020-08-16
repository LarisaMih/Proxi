package com.proxic.Proxi.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.proxic.Proxi.Entity.Sport;
import com.proxic.Proxi.Repositories.Hran;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController //определяет класс как Контроллер
@RequestMapping("/api/v1")
public class MessageController {

    @GetMapping("/olimp/{id}")
    public Object sport(@PathVariable("id")String id) throws InterruptedException, JsonProcessingException {
        RestTemplate restTemplate= new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        Hran response = restTemplate.getForObject("https://www.olimp.bet/apiru/live/sport?id="+id,Hran.class
        );
String allResponse = objectMapper.writeValueAsString(response);
       String nameSport = JsonPath.read(allResponse,"sport.name");
       Sport sport = new Sport();
       sport.setSport(nameSport);
        return sport;
    }
//

}
