package com.example.demo.controller;

import com.example.demo.entity.User;
import jdk.internal.org.objectweb.asm.commons.Method;
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
    //强制登陆页面
    @RequestMapping(value = "/")
    public String index(){
        return  "login";
    }
    //处理登陆的验证
   @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("userId") String userId,
                              @RequestParam("pwd") String pwd,
                              HttpServletRequest request){
       User user = regService.regUser(userId,pwd);
       if(user!=null) {
           ModelAndView modelAndView = new ModelAndView("welcome");
           modelAndView.addObject("userName",user.getName());
           request.getSession().setAttribute("userName",user.getName());
          request.getSession().setAttribute("userId",user.getU_id());
               return modelAndView;

       }
       else
       {
           ModelAndView modelAndView = new ModelAndView("fail");
           return modelAndView;
       }
   }
   //方便返回主页
   @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ModelAndView returnhome(HttpServletRequest request){
       if(request.getSession().getAttribute("userName")!=null)
       {ModelAndView modelAndView = new ModelAndView("welcome");
           modelAndView.addObject("userName",request.getSession().getAttribute("userName"));
           return modelAndView;
       }
       else
       {
           ModelAndView modelAndView = new ModelAndView("fail");
           return modelAndView;
       }
   }
   //查询余额
   @RequestMapping(value = "/user/selectbalance")
    public String selectbalance(HttpServletRequest request,Model model){
        String userId =(String)request.getSession().getAttribute("userId");
      int balance = regService.sBalance(userId);
       model.addAttribute("userbalance",balance);
       return "select";
   }
//返回取钱的页面
    @RequestMapping(value = "/user/withdraw" ,method = RequestMethod.GET)
    public String withdrwaview()
    {
        return "withdraw";
    }
    //验证取钱的细节
    @RequestMapping(value = "/user/withdraw",method = RequestMethod.POST)
    public String withdrawBalance(  @RequestParam("money") String money
                                   ,HttpServletRequest request)
    {
        Integer m = Integer.parseInt(money);//要取得钱
        String userId = (String)request.getSession().getAttribute("userId");
        int balance = regService.sBalance(userId);//取之前得余额
        if(balance>m)
        {
            regService.wBalance(userId,balance-m);//把剩下得钱进行更新
            return "success";
        }
        else
        {
            return "fail";
        }
    }
    //返回一个存钱的页面
@RequestMapping(value = "/user/deposit" ,method = RequestMethod.GET)
    public String depoistview(){
        return "deposit";
    }
    //验证存钱的细节
    @RequestMapping(value = "/user/deposit",method = RequestMethod.POST)
    public String depositBalance(@RequestParam("money") String money,
                                 HttpServletRequest request)
    {
        Integer m = Integer.valueOf(money);
        String userId = (String)request.getSession().getAttribute("userId");
        int balance =regService.sBalance(userId);
        int afterbalance = balance+m;
        regService.dBalance(userId,afterbalance);
        return "success";
    }
    //返回一个转账的页面
    @RequestMapping(value = "/user/transfer",method = RequestMethod.GET)
    public String transferview(){
        return "transfer";
    }
    //实现转账细节
    @RequestMapping(value = "/user/transfer",method = RequestMethod.POST)
    public String transferBalance(@RequestParam("userId") String userId,
                                  @RequestParam("money") String money,
                                  HttpServletRequest request)
    {

        Integer m = Integer.valueOf(money);//转给人家的钱
        String selfUserId = (String)request.getSession().getAttribute("userId");//自己的Id
        int s_balance = regService.sBalance(selfUserId);
        String othersUserId = userId;//人家的ID
        int  o_balance = regService.sBalance(othersUserId);
        //相当于自己取钱，人家存钱,service已经加了事务管理，保证了原子性
       if(s_balance>m)
       {
         //更新取钱后的值,更新存钱后的值
           regService.tBalance(selfUserId,s_balance-m,othersUserId,o_balance+m);
           return "success";
       }
       else
       {
           return "fail";
       }

    }

}
