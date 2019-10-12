package edu.hubu.learn.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.User;
import edu.hubu.learn.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public ModelAndView user(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(id);
        mav.addObject("user", user);
        mav.setViewName("user");
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        userService.deleteUser(id);
        ModelAndView mav = new ModelAndView("redirect:/user/list");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView users() {
        ModelAndView mav = new ModelAndView();
        List<User> users = userService.getUsers();
        mav.addObject("users", users);
        mav.setViewName("users");
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user_add");
        return mav;
    }

    @RequestMapping("/do_add")
    public ModelAndView doAddUser(User user) {
        userService.addUser(user);
        ModelAndView mav = new ModelAndView("redirect:/user/list");
        return mav;
    }

    @RequestMapping("/modify/{id}")
    public ModelAndView modifyUser(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", userService.getUser(id));
        mav.setViewName("user_modify");
        return mav;
    }

    @RequestMapping("/do_modify")
    public ModelAndView doModifyUser(User user) {
        userService.modifyUser(user);
        ModelAndView mav = new ModelAndView("redirect:/user/list");
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView searchUser() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user_search");
        return mav;
    }

    @RequestMapping("/do_search")
    public ModelAndView doSearchUser(HttpServletRequest httpRequest) {
        ModelAndView mav = new ModelAndView();
        String keyword = httpRequest.getParameter("keyword");
        List<User> users = userService.searchUsers(keyword);
        mav.addObject("users", users);
        mav.setViewName("users");
        return mav;
    }
}