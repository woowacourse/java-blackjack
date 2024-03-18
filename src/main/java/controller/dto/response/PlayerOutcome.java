package controller.dto.response;

import domain.constants.Outcome;
import domain.participant.Player;

public record PlayerOutcome(
        Player player,
        Outcome outcome
) {
}
