package com.myplanet.events.repository;

import com.myplanet.events.entity.Events;
import com.myplanet.events.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Message,Long> {
    List<Message> findByEvent(Events event, Pageable pageable);
}
