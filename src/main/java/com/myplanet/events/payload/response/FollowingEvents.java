package com.myplanet.events.payload.response;

import com.myplanet.events.entity.Events;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class FollowingEvents {

    private List<Events> event;
    private String username;
}
