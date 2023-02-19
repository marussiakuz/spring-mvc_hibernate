package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.UserInDto;
import web.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getListOfUsers(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize, ModelMap model,
                                 HttpSession session) {
        model.addAttribute("users", userService.getAll(pageNumber, pageSize));
        model.addAttribute("totalCount", userService.getTotalCount());
        session.setAttribute("pageNumber", pageNumber);
        session.setAttribute("pageSize", pageSize);
        return "users";
    }

    @PostMapping
    public String create(@RequestBody UserInDto user) {
        userService.create(user);
        return "users";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable long id, @RequestBody UserInDto user) {
        userService.update(id, user);
        return "users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "users";
    }
}
