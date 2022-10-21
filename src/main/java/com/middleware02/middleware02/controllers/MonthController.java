package com.middleware02.middleware02.controllers;

import com.middleware02.middleware02.entities.Month;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/months")
public class MonthController {

    @GetMapping
    public Month getMonth(Month month){
        return month;
    }


}
