package domain.betting;

import domain.money.BettingMoney;
import domain.participant.Player;

public record Betting(Player player, BettingMoney bettingMoney) {
}
