package com.galvanize.guestbook.unittest;

import com.galvanize.guestbook.dto.VisitorCommentDto;
import com.galvanize.guestbook.entity.VisitorCommentEntity;
import com.galvanize.guestbook.repository.VisitorCommentRepository;
import com.galvanize.guestbook.service.VisitorCommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuestBookServiceTest {

    @Mock
    VisitorCommentRepository mockCommentRepository;

    @InjectMocks
    VisitorCommentService visitorCommentService;

    @Test
    public void addComment() {
        var visitorComment = new VisitorCommentDto("David", "First Comment");
        visitorCommentService.addComment(visitorComment);

        verify(mockCommentRepository).save(new VisitorCommentEntity("David", "First Comment"));
    }

    @Test
    public void getAllComments() {
        when(mockCommentRepository.findAll()).thenReturn(List.of(
                new VisitorCommentEntity("David", "First Comment"),
                new VisitorCommentEntity("Wes", "Second Comment")
        ));

        var actual = visitorCommentService.getAllComments();

        assertThat(actual).isEqualTo(List.of(
                new VisitorCommentDto("David", "First Comment"),
                new VisitorCommentDto("Wes", "Second Comment")
        ));
    }

}
