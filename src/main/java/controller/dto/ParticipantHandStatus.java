package controller.dto;

import domain.participant.Hand;

public record ParticipantHandStatus(
        String name,
        Hand hand
) {
}
