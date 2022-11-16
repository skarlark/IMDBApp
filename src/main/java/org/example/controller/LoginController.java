package org.example.controller;

import org.example.dao.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class LoginController {

    private static Map<String, String> userCredentials;

    static {
        userCredentials = new HashMap<>();
        userCredentials.put("Test1", "Test1");
        userCredentials.put("Test2", "Test2");
        userCredentials.put("Test3", "Test3");
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        return userCredentials.get(user.getUsername()) != null && userCredentials.get(user.getUsername()).equals(user.getPassword());
    }

}
