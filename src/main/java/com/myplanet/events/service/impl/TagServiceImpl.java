package com.myplanet.events.service.impl;

import com.myplanet.events.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

   // @Auto
//    TagRepository repository;
//
//    @Override
//    public Tag getTagById(Long id) {
//        return repository.findById(id).orElseThrow(RuntimeException::new);
//    }
//
//    @Override
//    public Tag getTagByName(String name) {
//        return repository.findTagByName(name).orElseThrow(RuntimeException::new);
//    }

//    @Override
//    public Tag createNewTag(String name) {
//        Tag newTag = new Tag();
//        newTag.setName(name);
//        newTag.setTagUseCounter(1);
//        newTag.setDateCreated(new Date());
//        newTag.setDateLastModified(new Date());
//        return repository.save(newTag);
//    }
//
//    @Override
//    public Tag increaseTagUseCounter(String name) {
//        Tag targetTag = getTagByName(name);
//        targetTag.setTagUseCounter(targetTag.getTagUseCounter()+1);
//        targetTag.setDateLastModified(new Date());
//        return repository.save(targetTag);
//    }
//
//    @Override
//    public Tag decreaseTagUseCounter(String name) {
//        Tag targetTag = getTagByName(name);
//        targetTag.setTagUseCounter(targetTag.getTagUseCounter()-1);
//        targetTag.setDateLastModified(new Date());
//        return repository.save(targetTag);
//    }

//    @Override
//    public List<Tag> getTimelineTags() {
//        return repository.findAll(
//                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "tagUseCounter"))
//        ).getContent();
//    }
}
