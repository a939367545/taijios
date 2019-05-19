package com.sghy1801.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sghy1801.entity.User;
import com.sghy1801.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 登录
     *
     * @param phone
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String login(String phone,
                        String password, Model model) {
        User loginUser = userService.login(phone, password);
        String result = "";
        if (loginUser.getUserorman() == 1) {
            model.addAttribute("loginss", "no");
            result = "forward:index.jsp";

        } else {
            if (loginUser.getState() == 1) {
                model.addAttribute("loginNo", "no");
                result = "forward:index.jsp";
            } else {
                if (loginUser != null) {
                    // 登录成功
                    model.addAttribute("loginUser", loginUser);
                    result = "main";
                } else {
                    model.addAttribute("loginFlag", "error");
                    result = "forward:index.jsp"; // 破坏视图解析器的跳转
                }

            }
        }

        return result;
    }

    // 登出
    @RequestMapping("loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:index.jsp";
    }

    /**
     * 查询用户列表
     *
     * @param currentPage
     * @param username
     * @param phone
     * @return
     */
    @RequestMapping("/jsp/user/userlist")
    @ResponseBody
    public String userList(Integer currentPage, String username, String phone) {

        //获取用户列表
        List<User> list = userService.listUser(currentPage, username, phone);
        //获取用户记录数
        int count = userService.countUser(username, phone);
        //总页数
        int totalPage = count % 4 == 0 ? count / 4 : count / 4 + 1;
        //包装数据
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("list", list);
        param.put("count", count);
        param.put("totalPage", totalPage);
        return JSON.toJSONString(param);
    }

    //删除用户
    @RequestMapping("/jsp/user/deleteUser")
    @ResponseBody
    public String deleteUser(Integer userId, Model model) {
        System.out.println(userId);
        int count = userService.deleteUser(userId);
       /* if (count > 0) {
            model.addAttribute("deleteFlag", "ok");
        } else {
            model.addAttribute("deleteFlag", "error");
        }
        return "userlist";*/
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("count", count);
        System.out.println(count);
        return JSONObject.toJSONString(param);
    }


    @RequestMapping("/toupdateuser")
    public String toupdateuser(int id, Model model) {
        User u = userService.findById(id);
        model.addAttribute("user", u);
        return "user/updateuser";
    }

    @RequestMapping("jsp/getDate")
    @ResponseBody
    public String getDate() {
        List<Map> list = userService.getDate();
        JSONObject jsonObject = new JSONObject();
        Object[] arr = new Object[12];
        for (Map map : list) {
            int month = Integer.parseInt(map.get("registerDate").toString().substring(5, 7));
            arr[month - 1] = map.get("num");
        }
        return JSONObject.toJSONString(arr);
    }

    //更新状态
    @RequestMapping("/jsp/user/changes")
    @ResponseBody
    public String changes(int userId, int changestate, HttpSession session) {
       /* int count;
        Map<String, Object> param = new HashMap<String, Object>();
        if (((User) session.getAttribute("loginUser")).getUserorman() == 0) {
             count = 0;
        } else {
             count = userService.updateChanges(userId, changestate);
        }
        param.put("count", count);
        return JSONObject.toJSONString(param);*/
        int count = userService.updateChanges(userId, changestate);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("count", count);
        return JSONObject.toJSONString(param);
    }

}
