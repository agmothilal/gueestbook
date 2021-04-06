package com.galvanize.guestbook.repository;

import com.galvanize.guestbook.entity.VisitorCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorCommentRepository extends JpaRepository<VisitorCommentEntity, Long> {
}
