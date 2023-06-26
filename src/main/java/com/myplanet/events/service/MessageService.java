package com.myplanet.events.service;

import com.myplanet.events.entity.Message;

import java.util.List;

public interface MessageService {

    Message getMessageById(Long id);
    Message createNewMessage(String message, Long eventId, String author);
    Message updateMessage(Long messageId, String message);

    Message createEventMessage(Long eventId, String content, String username);

    Message updateEventMessage(Long messageId, String message);

    void deleteEventMessage(Long messageId, Long eventId);
    void deleteMessage(Long messageId);

    public List<Message> getPostMessagePaginate(Long eventId, Integer page, Integer size);


}
