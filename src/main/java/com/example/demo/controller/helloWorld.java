package com.example.demo.controller;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.service.*;


@Controller
public class helloWorld {
    @Autowired(required = true)
    private IRegService regService;
    @RequestMapping(value = "/")
    public String index(){
        return  "index";
    }
   @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String home()
    {
        return "login";
    }
   @RequestMapping(value = "/loginPost",method = RequestMethod.POST)

    public String login(@RequestParam("userId") String userId,
                        @RequestParam("pwd") String pwd,
                         Model model){
       User user = regService.regUser(userId,pwd);
       if(user!=null) {
          model.addAttribute("user",user);
           //System.out.println(user.getBalance()+" "+ user.getUserId());
               return "welcome";

       }
       else
           return "fail";
   }
   @RequestMapping(value = "/welcome")
   public String welcome(){
        return "welcome";
   }
   @RequestMapping(value = "/fail")
   public String fail(){
       return "fail";
   }


}
