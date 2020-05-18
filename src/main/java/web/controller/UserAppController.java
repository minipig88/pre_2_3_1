package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.models.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserAppController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ListAllUsersPage(ModelMap modelMap) {
        List<User> usersList = userService.getAllUsers();
        modelMap.addAttribute("userList", usersList);
        return "allUsersList";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String ViewAddUser(ModelMap modelMap) {
        return "addUser";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String AddUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        userService.addUser(new User(name, age));
        return "redirect:/";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String DeleteUser(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        userService.deleteUser(new User(id, name, age));
        return "redirect:/";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String ViewEditUser(HttpServletRequest request, ModelMap modelMap) {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        modelMap.addAttribute("user", new User(id, name, age));
        return "editUser";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String EditUser(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        userService.editUser(new User(id, name, age));
        return "redirect:/";
    }
}
