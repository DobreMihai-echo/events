package com.myplanet.events.repository;

import com.myplanet.events.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Events,Long> {

    List<Events> findByDateBeforeAndCompletedIsFalse(LocalDateTime date);
    List<Events> findAllByCompletedIsFalse();
//    List<Events> findAllByCompletedIsTrueAndEventJoinersIn(String followerList);
}
