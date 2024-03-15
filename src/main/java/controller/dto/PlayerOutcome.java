package controller.dto;

import domain.constants.Outcome;
import domain.participant.Player;

public record PlayerOutcome(
        Player player,
        Outcome outcome
) {
}
