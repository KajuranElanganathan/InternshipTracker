package com.example;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/home")
    public String home() {return "home!";}

    @GetMapping("/secured")
    public String secured() {return "secured!";}



}