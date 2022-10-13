//package com.example.demo.Controllers;
//
//import com.example.demo.Models.Computer;
//import com.example.demo.Repo.ComputerRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class ApiControllers {
//
//    @Autowired
//    private ComputerRepo computerRepo;
//
//    @GetMapping("/")
//    public String getString() {
//        return "siemaneczko";
//    }
//
//    @GetMapping("/computers")
//    public List<Computer> getComputers() {
//        return computerRepo.findAll();
//    }
//}