package blackjack.domain.strategy;

import blackjack.domain.BettingMoney;
import blackjack.domain.player.Player;

public interface BetInputStrategy {
    BettingMoney inputBettingMoney(Player player);
}
