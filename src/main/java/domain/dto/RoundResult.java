package domain.dto;

import domain.BetMoney;
import domain.participant.Player;

public record RoundResult(
        Player player,
        BetMoney betMoney
) {
}
