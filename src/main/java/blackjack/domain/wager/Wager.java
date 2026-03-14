package blackjack.domain.wager;

import blackjack.domain.money.Money;
import blackjack.domain.participant.Player;

public record Wager(Player player, Money money) {
}
