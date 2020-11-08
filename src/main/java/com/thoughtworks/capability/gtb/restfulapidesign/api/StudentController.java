package com.thoughtworks.capability.gtb.restfulapidesign.api;


import com.thoughtworks.capability.gtb.restfulapidesign.persistence.po.StudentPO;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:1234")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentPO>> getStudents() {
        List<StudentPO> studentPOS = studentService.getStudents();
        return ResponseEntity.ok(studentPOS);
    }


}
