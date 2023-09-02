package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(name = "count", defaultValue = "15") int count) {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> processAddForm(@ModelAttribute User user, @RequestParam String role) {
        user.addRole(role);
        userService.createUser(user);
        return new ResponseEntity<>(userService.findUserByEmail(user.getEmail()),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public String processEditForm(@PathVariable Long id, @ModelAttribute User user, @RequestParam String role) {
        //userService.updateUser(id, user, role);
        return "edit";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> processDeleteForm(@PathVariable Long id) {
        //System.out.println(id);
        //userService.deleteUser(id);
        return ResponseEntity.ok("ok");
    }
}
