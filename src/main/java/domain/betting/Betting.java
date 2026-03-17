package domain.betting;

import domain.money.Money;
import domain.participant.Player;

public record Betting(Player player, Money money) {
}
