package com.galvanize.guestbook.controller;

import com.galvanize.guestbook.dto.VisitorCommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GuestBookController {

    private static List<VisitorCommentDto> commentsDto = new ArrayList<>();

    @PostMapping(value = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@RequestBody VisitorCommentDto commentDto) {
        commentsDto.add(commentDto);
    }

    @GetMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<VisitorCommentDto> getComments(){
        return this.commentsDto;
    }
}
