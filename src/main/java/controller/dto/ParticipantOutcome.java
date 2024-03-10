package controller.dto;

import domain.constants.Outcome;

public record ParticipantOutcome(
        String name,
        Outcome outcome
) {
}
