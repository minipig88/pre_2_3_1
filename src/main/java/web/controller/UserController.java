package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String listAllUsersPage(ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.getAllUsers());
        return "allUsersList";
    }

    @GetMapping(value = "new")
    public String viewAddUser() {
        return "addUser";
    }

    @PostMapping(value = "new")
    public String AddUser(User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "delete")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return "redirect:/";
    }

    @PostMapping(value = "/editForm")
    public String getEdit(User user, Model model) {
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUser(User user) {
        userService.editUser(user);
        return "redirect:/";
    }
}
