package com.galvanize.guestbook.service;

import com.galvanize.guestbook.dto.VisitorCommentDto;
import com.galvanize.guestbook.entity.VisitorCommentEntity;
import com.galvanize.guestbook.repository.VisitorCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorCommentService {

    private final VisitorCommentRepository visitorCommentRepository;

    public VisitorCommentService(VisitorCommentRepository visitorCommentRepository) {
        this.visitorCommentRepository = visitorCommentRepository;
    }

    public void addComment(VisitorCommentDto commentDto) {
        var entity = new VisitorCommentEntity(commentDto.getName(), commentDto.getComment());
        visitorCommentRepository.save(entity);
    }

    public List<VisitorCommentDto> getAllComments() {
        var comments = visitorCommentRepository.findAll();
        return comments.stream()
                .map(entity -> new VisitorCommentDto(entity.getName(), entity.getComment()))
                .collect(Collectors.toList());
    }
}
