package domain.dto;

import domain.participant.BetMoney;
import domain.participant.Player;

public record Profit(
        Player player,
        BetMoney betMoney
) {
}
