package domain;

import domain.participant.Player;

public record RoundResult(
        Player player,
        Result result
) {
}
