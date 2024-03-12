package controller.dto;

import domain.constants.Outcome;

public record PlayerOutcome(
        String name,
        Outcome outcome
) {
}
