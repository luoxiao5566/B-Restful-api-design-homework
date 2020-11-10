package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_get_all_students() throws Exception {
        mockMvc.perform(get("/v1/students"))
                .andExpect(jsonPath("$", hasSize(15)))
                .andExpect(jsonPath("$[0].name", is("成吉思汗")))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].gender", is("male")))
                .andExpect(jsonPath("$[3].name", is("钟无艳")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].gender", is("female")))
                .andExpect(jsonPath("$[14].name", is("蔡文姬")))
                .andExpect(jsonPath("$[14].id", is(15)))
                .andExpect(jsonPath("$[14].gender", is("female")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_create_student() throws Exception {
        Student student = Student.builder().name("孙悟空").gender("male").note("猴哥无敌").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(student);
        mockMvc.perform(post("/v1/students").content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/v1/students"))
                .andExpect(jsonPath("$", hasSize(16)))
                .andExpect(jsonPath("$[15].name", is("孙悟空")))
                .andExpect(jsonPath("$[15].id", is(16)))
                .andExpect(jsonPath("$[15].gender", is("male")))
                .andExpect(jsonPath("$[15].note", is("猴哥无敌")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_students_by_id() throws Exception {
        mockMvc.perform(get("/v1/students/4"))
                .andExpect(jsonPath("$.name", is("钟无艳")))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.gender", is("female")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_students_by_female() throws Exception {
        mockMvc.perform(get("/v1/students?gender=female"))
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$[0].name", is("鲁班七号")))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].gender", is("female")))
                .andExpect(jsonPath("$[5].name", is("大乔")))
                .andExpect(jsonPath("$[5].id", is(14)))
                .andExpect(jsonPath("$[5].gender", is("female")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_students_by_male() throws Exception {
        mockMvc.perform(get("/v1/students?gender=male"))
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[1].name", is("太乙真人")))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].gender", is("male")))
                .andExpect(jsonPath("$[7].name", is("哪吒")))
                .andExpect(jsonPath("$[7].id", is(13)))
                .andExpect(jsonPath("$[7].gender", is("male")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_remove_student_by_id() throws Exception {
        mockMvc.perform(delete("/v1/students/4")).andExpect(status().isOk());

        mockMvc.perform(get("/v1/students"))
                .andExpect(jsonPath("$", hasSize(14)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_update_student_by_id() throws Exception {
        Student student = Student.builder().name("大漂亮").note("漂亮无敌").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(student);
        mockMvc.perform(patch("/v1/students/4").content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/v1/students/4"))
                .andExpect(jsonPath("$.name", is("大漂亮")))
                .andExpect(jsonPath("$.gender", is("female")))
                .andExpect(jsonPath("$.note",is("漂亮无敌")))
                .andExpect(status().isOk());
    }
}
