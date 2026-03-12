package domain.betting;

import domain.Money;
import domain.Player;

public record Betting(Player player, Money money) {
}
