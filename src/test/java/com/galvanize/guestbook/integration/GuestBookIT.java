package com.galvanize.guestbook.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.dto.VisitorCommentDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GuestBookIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @Order(value = 2)
    public void addCommentTest() throws Exception {
        var comment = new VisitorCommentDto("David", "First comment");

        var requestBuilder = post("/comment").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(comment));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    @Order(value = 1)
    public void allCommentsTest() throws Exception {
        this.mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("length()").value(0));

        var comment = new VisitorCommentDto("David", "First comment");

        var requestBuilder= post("/comment").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(comment));

        mockMvc.perform(requestBuilder);

        this.mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("$[0].name").value("David"))
                .andExpect(jsonPath("$[0].comment").value("First comment"));
    }

}
