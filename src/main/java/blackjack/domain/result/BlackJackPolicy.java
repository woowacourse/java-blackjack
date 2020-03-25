package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class BlackJackPolicy implements ResultPolicy {
    @Override
    public boolean judge(Player player, Dealer dealer) {

        return player.isBlackJack() && !dealer.isBlackJack();
    }
}
