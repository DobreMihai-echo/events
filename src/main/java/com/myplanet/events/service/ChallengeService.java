package com.myplanet.events.service;

import com.myplanet.events.entity.Challenge;
import com.myplanet.events.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository repository;

    public String saveChallenge(Challenge challenge) {
        System.out.println("MY CHALLENGE" + challenge.getChallengeTags());
        try {
            repository.save(challenge);
            return "Challenge was saved successfully";
        } catch (Exception ex) {
            return "There was a problem saving the challenge" + ex.getMessage();
        }
    }

    public List<Challenge> getAllChallenges(String username) {

        List<Challenge> challenges = repository.findAll();
        return challenges.stream().filter(challenge -> !challenge.getChallengeJoiners().contains(username) && !challenge.getCompletedJoiners().contains(username)).collect(Collectors.toList());
    }

    public Challenge getChallengeById(Long id) {
        return repository.findById(id).get();
    }

    public String updateChallenge(Challenge challenge) {
        Challenge challengeToUpdate = repository.findById(challenge.getId()).get();

        challengeToUpdate.setTitle(challenge.getTitle());
        challengeToUpdate.setDescription(challenge.getDescription());
        challengeToUpdate.setLevel(challenge.getLevel());
        challengeToUpdate.setPoints(challenge.getPoints());

        try {
            repository.save(challenge);
            return "Challenge was updated successfully";
        } catch (Exception ex) {
            return "There was an error updating the challenge";
        }
    }

    public String deleteChallenge(Long id) {
        try {
            repository.deleteById(id);
            return "Challenge deleted successfully";
        } catch (Exception ex) {
            return "There was an error deleting the challenge";
        }
    }

    public void joinChallenge(List<Long> challengeId, String username) {
        List<Challenge> knownOngoingChallenges = repository.findAll();
        knownOngoingChallenges = knownOngoingChallenges.stream().filter(challenge -> challenge.getChallengeJoiners().contains(username)).collect(Collectors.toList());
        for (Challenge knownChallenges: knownOngoingChallenges) {
            if (!challengeId.contains(knownChallenges.getId())) {
                knownChallenges.getChallengeJoiners().remove(username);
                repository.save(knownChallenges);
            }
        }
        for (Long id: challengeId) {
            Challenge challenge = repository.findById(id).orElseThrow(RuntimeException::new);
            if (!challenge.getChallengeJoiners().contains(username)) {
                challenge.getChallengeJoiners().add(username);
                repository.save(challenge);
            }
        }
    }

    public void completeChallenge(Long challengeId, String username) {
        Challenge challenge = repository.findById(challengeId).orElseThrow(RuntimeException::new);
        challenge.getChallengeJoiners().remove(username);
        challenge.getCompletedJoiners().add(username);
        repository.save(challenge);
    }

    public void unjoinChallenge(Long challengeId, String username) {
        Challenge challenge = repository.findById(challengeId).orElseThrow(RuntimeException::new);
        challenge.getChallengeJoiners().remove(username);
        repository.save(challenge);
    }

    public List<Challenge> getOngoingChallengesForUser(String username) {
        List<Challenge> allChallenges = repository.findAll();
        return allChallenges.stream().filter(challenge -> challenge.getChallengeJoiners().contains(username)).collect(Collectors.toList());
    }

    public List<Challenge> getCompletedChallengesForUser(String username) {
        List<Challenge> allChallenges = repository.findAll();
        return allChallenges.stream().filter(challenge -> challenge.getCompletedJoiners().contains(username)).collect(Collectors.toList());
    }
}
