package com.myplanet.events.entity;

import lombok.*;

import javax.persistence.*;


@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Column
    private String tagName;

    @Column
    private String action;



}
