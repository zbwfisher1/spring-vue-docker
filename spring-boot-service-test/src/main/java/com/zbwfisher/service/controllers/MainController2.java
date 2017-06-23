package com.zbwfisher.service.controllers;

import com.google.gson.Gson;
import com.zbwfisher.service.configs.CDConfig;
import com.zbwfisher.service.configs.Wisely2Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Import(CDConfig.class)
public class MainController2 {

  @RequestMapping("/test")
  @ResponseBody
  public String index(Wisely2Settings wisely2Settings) {

    Gson gson = new Gson();
    System.out.println(wisely2Settings.getGender());

    return "test: MainController2" ;
  }

}
