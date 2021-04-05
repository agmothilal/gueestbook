package com.galvanize.guestbook.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.dto.VisitorCommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GuestBookIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void addCommentTest() throws Exception {
        var comment = new VisitorCommentDto("David", "First comment");

        var requestBuilder = post("/comment").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(comment));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

}
