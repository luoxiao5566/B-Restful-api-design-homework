package com.thoughtworks.capability.gtb.restfulapidesign.persistence.po;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;

import java.util.List;

public class GroupPO {
    private Integer id;
    private String name;
    private List<Student> students;
    private String note;
}
