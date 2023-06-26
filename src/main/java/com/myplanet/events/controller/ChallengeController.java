package com.myplanet.events.controller;

import com.myplanet.events.entity.Challenge;
import com.myplanet.events.service.ChallengeService;
import com.myplanet.events.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/challenge")
@CrossOrigin(origins = "http://localhost:8085")
public class ChallengeController {

    @Autowired
    ChallengeService service;

    @Autowired
    TagService tagService;

    @GetMapping("{id}")
    public ResponseEntity<Challenge> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getChallengeById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Challenge>> getAll(@RequestParam(name = "username") String username) {
        return ResponseEntity.ok(service.getAllChallenges(username));
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody Challenge challengeRequest) {
        try {
            return ResponseEntity.ok(service.saveChallenge(challengeRequest));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Challenge challenge) {
        try {
            return ResponseEntity.ok(service.updateChallenge(challenge));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(service.deleteChallenge(id));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PutMapping("/join")
    public ResponseEntity<?> join(@RequestBody List<Long> challengeId, @RequestParam(name = "username") String username) {
        try {
            service.joinChallenge(challengeId, username);
            return ResponseEntity.ok("Success");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Nope");
        }
    }

    @PutMapping("/unjoin")
    public ResponseEntity<?> unjoin(@RequestParam(name = "challengeId") Long challengeId, @RequestParam(name = "username") String username) {
        try {
            service.unjoinChallenge(challengeId, username);
            return ResponseEntity.ok("Successs");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Nope");
        }
    }

    @GetMapping("/ongoing")
    public ResponseEntity<?> getOngoingChallengesForUser(@RequestParam(name = "username") String username) {
        try {
            return ResponseEntity.ok(service.getOngoingChallengesForUser(username));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("There was a problem");
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<?> getCompletedChallengesForUser(@RequestParam(name = "username") String username) {
        try {
            return ResponseEntity.ok(service.getCompletedChallengesForUser(username));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("There was a problem");
        }
    }

    @PutMapping("/complete")
    public ResponseEntity<?> completeChallenge(@RequestParam(name = "challengeID") Long id,@RequestParam(name = "username") String username) {
        service.completeChallenge(id,username);
        return ResponseEntity.ok("OK");
    }

//    @GetMapping("/tags/{tagName}")
//    public ResponseEntity<?> getPostsByTag(@PathVariable("tagName") String tagName,
//                                           @RequestParam("page") Integer page,
//                                           @RequestParam("size") Integer size) {
//        page = page < 0 ? 0 : page-1;
//        size = size <= 0 ? 5 : size;
//        Tag targetTag = tagService.getTagByName(tagName);
//        List<PostResponse> taggedPosts = postService.getPostByTagPaginate(targetTag, page, size);
//        return new ResponseEntity<>(taggedPosts, HttpStatus.OK);
//    }
}
