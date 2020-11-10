package com.thoughtworks.capability.gtb.restfulapidesign.persistence.impl;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.persistence.po.StudentPO;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepositoryImpl implements GroupRepository {

    private final StudentService studentService;

    public GroupRepositoryImpl(StudentService studentService) {
        this.studentService = studentService;
    }

}
