package com.example.demo.controller;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.example.demo.service.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class helloWorld {
    @Autowired(required = true)
    private IRegService regService;
    @RequestMapping(value = "/")
    public String index(){
        return  "login";
    }
   @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("userId") String userId,
                        @RequestParam("pwd") String pwd){
       User user = regService.regUser(userId,pwd);
       if(user!=null) {
           ModelAndView modelAndView = new ModelAndView("welcome");
           modelAndView.addObject("userId",userId);
               return modelAndView;

       }
       else
       {
           ModelAndView modelAndView = new ModelAndView("fail");
           return modelAndView;
       }
   }
   @RequestMapping(value = "/welcome")
   public String welcome (HttpServletRequest request, Model model){
        model.addAttribute("userId",request.getAttribute("userId"));
        return "welcome";
   }
   @RequestMapping(value = "/fail")
   public String fail(){
       return "fail";
   }


}
