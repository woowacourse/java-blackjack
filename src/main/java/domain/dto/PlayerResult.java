package domain.dto;

import domain.BetMoney;
import domain.participant.Player;

public record PlayerResult(
        Player player,
        BetMoney betMoney
) {
}
