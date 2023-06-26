package com.myplanet.events.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "challenge_table")
@Data
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creator")
    private String creator;

    @Column(name = "level")
    private String level;

    @Column(name = "points")
    private Long points;

    @Column(name = "color")
    private String color;

    @Column(name="ongoing_challenge")
    @ElementCollection
    private List<String> challengeJoiners;

    @Column(name="completed_challenge")
    @ElementCollection
    private List<String> completedJoiners;

    @Column(name="challenge_tags")
    @ElementCollection
    @CollectionTable(name = "tag1", joinColumns = @JoinColumn(name = "challenge_id"))
    private List<Tag> challengeTags = new ArrayList<>();
}
