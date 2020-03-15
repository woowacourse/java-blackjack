package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class PlayerResultMatcher {

    public static BlackJackResult match(Dealer dealer, Player player) {
        if (player.isBusted())
            return BlackJackResult.LOSE;
        if (dealer.isBusted())
            return BlackJackResult.WIN;
        return findWinner(dealer, player);
    }

    private static BlackJackResult findWinner(Dealer dealer, Player player) {
        if (dealer.calculateSum() < player.calculateSum()) {
            return BlackJackResult.WIN;
        }
        if (dealer.calculateSum() > player.calculateSum()) {
            return BlackJackResult.LOSE;
        }
        return BlackJackResult.DRAW;
    }
}