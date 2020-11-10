package com.thoughtworks.capability.gtb.restfulapidesign.persistence.impl;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.persistence.po.StudentPO;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private List<StudentPO> studentPOS = new ArrayList<>();

    {
        studentPOS.add(new StudentPO(1, "成吉思汗", "male", ""));
        studentPOS.add(new StudentPO(2, "鲁班七号", "female", ""));
        studentPOS.add(new StudentPO(3, "太乙真人", "male", ""));
        studentPOS.add(new StudentPO(4, "钟无艳", "female", ""));
        studentPOS.add(new StudentPO(5, "花木兰", "female", ""));
        studentPOS.add(new StudentPO(6, "雅典娜", "female", ""));
        studentPOS.add(new StudentPO(7, "芈月", "female", ""));
        studentPOS.add(new StudentPO(8, "白起", "male", ""));
        studentPOS.add(new StudentPO(9, "刘禅", "male", ""));
        studentPOS.add(new StudentPO(10, "庄周", "male", ""));
        studentPOS.add(new StudentPO(11, "马超", "male", ""));
        studentPOS.add(new StudentPO(12, "刘备", "male", ""));
        studentPOS.add(new StudentPO(13, "哪吒", "male", ""));
        studentPOS.add(new StudentPO(14, "大乔", "female", ""));
        studentPOS.add(new StudentPO(15, "蔡文姬", "female", ""));
    }

    @Override
    public List<StudentPO> findAll() {
        return studentPOS;
    }

    @Override
    public List<StudentPO> findByGender(String gender) {
        List<StudentPO> result = studentPOS.stream().filter((s) -> s.getGender().equals(gender))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public StudentPO findById(Integer id) {
        StudentPO studentPO = studentPOS.stream().filter((s) -> s.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("not find"));
        return studentPO;
    }

    @Override
    public void save(Student student) {
        int index = studentPOS.size() + 1;
        StudentPO studentPO = StudentPO.builder().id(index)
                .name(student.getName())
                .gender(student.getGender())
                .note(student.getNote()).build();
        studentPOS.add(studentPO);
    }

    @Override
    public void remove(Integer id) {
        StudentPO studentPO = studentPOS.stream().filter((s) -> s.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("not find"));
        int index = studentPO.getId() - 1;
        studentPOS.remove(index);
    }

    @Override
    public void update(Integer id, Student info) {
        StudentPO studentPO = StudentPO.builder().id(id)
                .name(info.getName())
                .gender(info.getGender())
                .note(info.getNote()).build();
        Optional.ofNullable(studentPO.getName()).ifPresent((n) -> studentPOS.get(id-1).setName(n));
        Optional.ofNullable(studentPO.getGender()).ifPresent((g) -> studentPOS.get(id-1).setGender(g));
        Optional.ofNullable(studentPO.getNote()).ifPresent((n) -> studentPOS.get(id-1).setNote(n));
    }
}
