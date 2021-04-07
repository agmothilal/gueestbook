package com.galvanize.guestbook.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.dto.VisitorCommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class GuestBookIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DirtiesContext
    public void addCommentTest() throws Exception {
        var comment = new VisitorCommentDto("David", "First comment");

        var requestBuilder = post("/comment").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(comment));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andDo(MockMvcRestDocumentation.document("AddComment"));
    }

    @Test
    @DirtiesContext
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
                .andExpect(jsonPath("$[0].comment").value("First comment"))
                .andDo(MockMvcRestDocumentation.document("Comments", PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("[0].name").description("Name of the person that is commenting."),
                        PayloadDocumentation.fieldWithPath("[0].comment").description("Is the commet.")
                )));
    }

}
