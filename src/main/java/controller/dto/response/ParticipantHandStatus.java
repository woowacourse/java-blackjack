package controller.dto.response;

import domain.participant.Hand;

public record ParticipantHandStatus(
        String name,
        Hand hand
) {
}
