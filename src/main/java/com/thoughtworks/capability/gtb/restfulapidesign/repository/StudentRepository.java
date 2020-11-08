package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.persistence.po.StudentPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {

    List<StudentPO> findAll();

    void save(Student student);
}
