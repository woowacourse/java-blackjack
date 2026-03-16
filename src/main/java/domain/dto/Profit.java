package domain.dto;

import domain.BetMoney;
import domain.participant.Player;

public record Profit(
        Player player,
        BetMoney betMoney
) {
}
