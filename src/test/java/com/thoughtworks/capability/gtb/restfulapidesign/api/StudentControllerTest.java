package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_get_all_students() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(jsonPath("$",hasSize(15)))
                .andExpect(jsonPath("$[0].name",is("成吉思汗")))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].gender",is("male")))
                .andExpect(jsonPath("$[3].name",is("钟无艳")))
                .andExpect(jsonPath("$[3].id",is(4)))
                .andExpect(jsonPath("$[3].gender",is("female")))
                .andExpect(jsonPath("$[14].name",is("蔡文姬")))
                .andExpect(jsonPath("$[14].id",is(15)))
                .andExpect(jsonPath("$[14].gender",is("female")))
                .andExpect(status().isOk());
    }

}
