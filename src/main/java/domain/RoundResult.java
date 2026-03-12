package domain;

import domain.participant.Player;

public record RoundResult(
        Player player,
        BetMoney betMoney
) {
}
