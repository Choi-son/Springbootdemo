package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.service.*;
import org.springframework.web.servlet.ModelAndView;



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
                              @RequestParam("pwd") String pwd,
                              HttpServletRequest request){
       User user = regService.regUser(userId,pwd);
       if(user!=null) {
           ModelAndView modelAndView = new ModelAndView("welcome");
           modelAndView.addObject("userName",user.getName());
          request.getSession().setAttribute("userId",user.getU_id());
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
        model.addAttribute("userName",request.getAttribute("userName"));
        return "welcome";
   }
   @RequestMapping(value = "/fail")
   public String fail(){
       return "fail";
   }
   @RequestMapping(value = "/user/selectbalance")
    public String selectbalance(HttpServletRequest request,Model model){
        String userId =(String)request.getSession().getAttribute("userId");
      int balance = regService.sBalance(userId);
       model.addAttribute("userbalance",balance);
       return "select";
   }
//取钱的先formaction一个url 再通过另外一个控制器进行Post验证操作
}
