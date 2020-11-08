package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.persistence.po.StudentPO;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentPO> getStudents() {
        return studentRepository.findAll();
    }

    public List<StudentPO> getStudentsByGender(String gender) {
        return studentRepository.findByGender(gender);
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public StudentPO getStudentById(Integer id) {
        StudentPO studentPO = studentRepository.findById(id);
        return studentPO;
    }

}
