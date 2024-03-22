package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class ResultJudge {

    public PlayerResult judgePlayerResult(Dealer dealer, Player player) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return PlayerResult.BLACKJACK_WIN;
        }
        if (playerLose(dealer, player)) {
            return PlayerResult.LOSE;
        }
        if (dealer.score().isSameTo(player.score())) {
            return PlayerResult.PUSH;
        }
        return PlayerResult.WIN;
    }

    private boolean playerLose(Dealer dealer, Player player) {
        return player.isBust() || (dealer.isBlackjack() && !player.isBlackjack())
                || (dealer.score().isBiggerThan(player.score()) && !dealer.isBust());
    }
}
