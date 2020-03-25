package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class LosePolicy implements ResultPolicy {
    @Override
    public boolean judge(Player player, Dealer dealer) {
        return player.isBusted() ||
                (!player.isBlackJack() && dealer.isBlackJack()) ||
                (player.calculateScore() < dealer.calculateScore());
    }
}
