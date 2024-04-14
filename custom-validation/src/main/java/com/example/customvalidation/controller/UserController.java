//package com.example.customvalidation.controller;
//
//import com.example.customvalidation.entity.User;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("user")
//@Validated
//public class UserController {
//    @PostMapping()
//    public ResponseEntity<String> create(@Valid @RequestBody User user) {
//        System.out.println("================" + user);
//        return ResponseEntity.ok("ok");
//    }
//}
