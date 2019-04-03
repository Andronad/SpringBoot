package com.iba.courses.rest;

import com.iba.courses.domain.Student;
import com.iba.courses.service.IMSService;
import com.iba.courses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/student")
public class StudentApi {

    @Autowired
    private StudentService studentService;
    private IMSService imsService = new IMSService();

    @CrossOrigin("http://localhost:4200")

    @GetMapping("/getAllStudents")
    private List<Student> getAllStudents() {
        System.out.println("REST");
        return studentService.getAllStudents();
    }

    @GetMapping("/saveStudent")
    private List<Student> saveStudent() {
        System.out.println("REST");
        return studentService.saveStudent();
    }

    //IMS
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/initializeIMS/{myCommand}")
    private Map initIMS(@PathVariable String myCommand) throws Exception {
        imsService.init();
        imsService.connect();
        return Collections.singletonMap("response", imsService.execute("/" + myCommand));
    }


    @GetMapping("/greeting/{myVariable}")
    private String greeting(@PathVariable String myVariable) {
        return "Hello " + myVariable;
    }

    @PostMapping("/simplePost")
    private void postMethod(@RequestBody String s) {
        System.out.println(s);
    }

    @PostMapping("/simplePost/{id}")
    private int postMethod(@RequestBody String s, @PathVariable int id) {
        System.out.println(s);
        return id;
    }
}
