package com.thoughtworks.capability.gtb.restfulapidesign.api;


import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.persistence.po.StudentPO;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:1234")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/v1/students")
    public ResponseEntity<List<StudentPO>> getStudents(@RequestParam(required = false) String gender) {
        if (null != gender) {
            return ResponseEntity.ok(studentService.getStudentsByGender(gender));
        }
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/v1/students/{id}")
    public ResponseEntity<StudentPO> getStudentById(@PathVariable Integer id) {
        StudentPO studentPO = studentService.getStudentById(id);
        return ResponseEntity.ok(studentPO);
    }

    @PostMapping("/v1/students")
    public ResponseEntity createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/v1/students/{id}")
    public ResponseEntity removeStudent(@PathVariable Integer id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/v1/students/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody Student info) {
        studentService.updateStudent(id, info);
        return ResponseEntity.ok().build();
    }


}
