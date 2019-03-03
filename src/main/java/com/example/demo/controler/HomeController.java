package com.example.demo.controler;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Slf4j
@Timed
@Controller
public class HomeController {

    private final BuildProperties buildProperties;
    private final GitProperties gitProperties;

    @Autowired
    public HomeController(BuildProperties buildProperties, GitProperties gitProperties) {
        this.buildProperties = buildProperties;
        this.gitProperties = gitProperties;
    }

    @GetMapping(value = {"/", "/hello"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity hello() {
        return ResponseEntity.ok(Map.of("buildProperties", buildProperties, "gitProperties", gitProperties));
    }

}
