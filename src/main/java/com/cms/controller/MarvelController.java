package com.cms.controller;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;


@RestController
public class MarvelController {

    @RequestMapping("/api/maa")
    public Object getMaa() throws IOException, ParseException {
        String file = MarvelController.class.getClassLoader().getResource("maa.json").getFile();

        return new JSONParser().parse(new FileReader(file));

    }
}
