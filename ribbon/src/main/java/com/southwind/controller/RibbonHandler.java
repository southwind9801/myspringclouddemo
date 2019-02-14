package com.southwind.controller;

import com.southwind.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RequestMapping("/ribbon")
@RestController
public class RibbonHandler {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public String index(){
        return restTemplate.getForObject("http://provider/student/index",String.class);
    }

    @GetMapping("/findAll")
    public Collection<Student> findAll(){
        return restTemplate.getForObject("http://provider/student/findAll",Collection.class);
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") long id){
        return restTemplate.getForObject("http://provider/student/findById/{id}",Student.class,id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student){
        restTemplate.postForObject("http://provider/student/save",student,Student.class);
    }

    @PutMapping("/update")
    public void update(@RequestBody Student student){
        restTemplate.put("http://provider/student/update",student);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        restTemplate.delete("http://provider/student/deleteById/{id}",id);
    }
}