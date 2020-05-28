package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserAppController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ListAllUsersPage(ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.getAllUsers());
        return "allUsersList";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String ViewAddUser() {
        return "addUser";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String AddUser(@RequestParam(required = true) String name,
                          @RequestParam(required = true) Integer age) {
        userService.addUser(new User(name, age));
        return "redirect:/";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String DeleteUser(@RequestParam(required = true) long id,
                             @RequestParam(required = true) String name,
                             @RequestParam(required = true) int age) {
        userService.deleteUser(new User(id, name, age));
        return "redirect:/";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String ViewEditUser(@RequestParam(required = true) long id,
                               @RequestParam(required = true) String name,
                               @RequestParam(required = true) int age,
                               ModelMap modelMap) {
        modelMap.addAttribute("user", new User(id, name, age));
        return "editUser";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String EditUser(@RequestParam(required = true) long id,
                           @RequestParam(required = true) String name,
                           @RequestParam(required = true) int age) {
        userService.editUser(new User(id, name, age));
        return "redirect:/";
    }
}
