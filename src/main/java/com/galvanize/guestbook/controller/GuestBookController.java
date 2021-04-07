package com.galvanize.guestbook.controller;

import com.galvanize.guestbook.dto.VisitorCommentDto;
import com.galvanize.guestbook.service.VisitorCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuestBookController {

    public VisitorCommentService visitorCommentService;

    @Autowired
    public GuestBookController(VisitorCommentService visitorCommentService) {
        this.visitorCommentService = visitorCommentService;
    }

    @PostMapping(value = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@RequestBody VisitorCommentDto commentDto) {
        this.visitorCommentService.addComment(commentDto);
    }

    @GetMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<VisitorCommentDto> getComments(){
        return this.visitorCommentService.getAllComments();
    }
}
