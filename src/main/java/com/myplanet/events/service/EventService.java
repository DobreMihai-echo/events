package com.myplanet.events.service;

import com.myplanet.events.entity.Events;
import com.myplanet.events.payload.response.FollowingEvents;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    List<Events> saveEvent(Events eventToInsert);
    List<Events> getEvents();

    List<Events> deleteEvent(Long eventId);

    List<Events> updateEvent(Long eventId, Events update);
    List<String> joinEvent(Long eventId,String usernameToJoin);
    List<String> unjoinEvent(Long eventId,String usernameToJoin);
    void markEventsAsCompleted();

//    List<FollowingEvents> getCompletedFollowerListEvents(List<String> followingUsernames);
}
