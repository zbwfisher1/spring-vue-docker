package com.zbwfisher.service.controllers;

import com.google.gson.Gson;
import com.zbwfisher.service.configs.Wisely2Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @Autowired
  Wisely2Settings wiselySettings;

  @RequestMapping("/")
  @ResponseBody
  public String index() {

//    Wisely2Settings wisely2Settings = new Wisely2Settings();
    Gson gson = new Gson();

//    System.out.println(gson.toJson(wiselySettings));
    System.out.println(wiselySettings.getGender());

    return "test: MainController";
  }

}
