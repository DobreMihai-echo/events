package com.myplanet.events.service.impl;

import com.myplanet.events.entity.Events;
import com.myplanet.events.entity.Message;
import com.myplanet.events.repository.CommentsRepository;
import com.myplanet.events.repository.EventRepository;
import com.myplanet.events.service.EventService;
import com.myplanet.events.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private CommentsRepository repository;

    @Autowired
    private EventRepository eventRepository;



    @Override
    public Message getMessageById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Message createNewMessage(String message, Long eventId, String author) {
        Message messageToCreate = new Message();
        Events event = eventRepository.findById(eventId).orElseThrow(RuntimeException::new);
        messageToCreate.setContent(message);
        messageToCreate.setAuthor(author);
        messageToCreate.setEvent(event);
        messageToCreate.setDateCreated(LocalDateTime.now());
        messageToCreate.setLastModified(LocalDateTime.now());
        return repository.save(messageToCreate);
    }

    @Override
    public Message updateMessage(Long messageId, String message) {
        Message messageToUpdate = repository.findById(messageId).orElseThrow(RuntimeException::new);
        messageToUpdate.setContent(message);
        messageToUpdate.setLastModified(LocalDateTime.now());
        return repository.save(messageToUpdate);
    }

    @Override
    public Message createEventMessage(Long eventId, String content, String username) {

        Events targetEvent = eventRepository.findById(eventId).orElseThrow(RuntimeException::new);
        Message message = this.createNewMessage(content,eventId,username);
        targetEvent.setCommentCount(targetEvent.getCommentCount()+1);
        eventRepository.save(targetEvent);
        return message;
    }

    @Override
    public Message updateEventMessage(Long messageId, String message) {
        return updateMessage(messageId,message);
    }

    @Override
    public void deleteEventMessage(Long messageId, Long eventId) {
        Events event = eventRepository.findById(eventId).orElseThrow(RuntimeException::new);
        this.deleteMessage(messageId);
        event.setCommentCount(event.getCommentCount() - 1);
        eventRepository.save(event);
    }

    @Override
    public void deleteMessage(Long messageId) {
        repository.deleteById(messageId);
    }

    @Override
    public List<Message> getPostMessagePaginate(Long eventId, Integer page, Integer size) {
        Events event = eventRepository.findById(eventId).orElseThrow(RuntimeException::new);
        List<Message> messages = repository.findByEvent(event, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));
        return messages;
    }
}
