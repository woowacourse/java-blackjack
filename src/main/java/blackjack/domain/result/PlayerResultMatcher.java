package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.rule.Score;

public class PlayerResultMatcher {

    public static BlackJackResult match(Dealer dealer, Player player) {
        if (player.isBusted()) {
            return BlackJackResult.LOSE;
        }
        if (dealer.isBusted()) {
            return BlackJackResult.WIN;
        }
        return findWinner(dealer, player);
    }

    private static BlackJackResult findWinner(Dealer dealer, Player player) {
        Score dealerScore = dealer.handScore();
        Score playerScore = player.handScore();

        int matchResult = playerScore.compareTo(dealerScore);

        if (matchResult > 0) {
            return BlackJackResult.WIN;
        }
        if (matchResult < 0) {
            return BlackJackResult.LOSE;
        }
        return BlackJackResult.DRAW;
    }
}