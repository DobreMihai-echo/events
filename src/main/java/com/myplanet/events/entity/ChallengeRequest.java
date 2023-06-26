package com.myplanet.events.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeRequest {

    Challenge challenge;
    List<Tag> challengeTags;
}
