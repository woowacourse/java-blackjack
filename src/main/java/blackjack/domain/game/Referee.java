package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class Referee {

    public PlayerResult judgePlayerResult(Dealer dealer, Player player) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return PlayerResult.BLACKJACK_WIN;
        }
        if (player.isBust() || dealer.isBlackjack()) {
            return PlayerResult.LOSE;
        }
        if (dealerLose(dealer, player)) {
            return PlayerResult.WIN;
        }
        if (dealer.score() == player.score()) {
            return PlayerResult.PUSH;
        }
        return PlayerResult.LOSE;
    }

    public boolean dealerLose(Dealer dealer, Player player) {
        return player.score() > dealer.score() ||
                dealer.isBust();
    }
}
