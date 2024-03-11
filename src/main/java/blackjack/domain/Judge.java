package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class Judge {

    private Judge() {
    }

    public static PlayerGameResult compare(Dealer dealer, Player player) {
        if (player.isBust()) {
            return blackjack.domain.PlayerGameResult.LOSE;
        }

        if (player.isBlackJack()) {
            return judgeWhenPlayerIsBlackjack(dealer);
        }

        return judgeWhenPlayerNormalScore(player, dealer);
    }

    private static PlayerGameResult judgeWhenPlayerIsBlackjack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return PlayerGameResult.PUSH;
        }

        return PlayerGameResult.BLACKJACK_WIN;
    }

    private static PlayerGameResult judgeWhenPlayerNormalScore(Player player, Dealer dealer) {
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();

        if (dealer.isBust() || playerScore > dealerScore) {
            return PlayerGameResult.WIN;
        }

        if (dealer.isBlackJack() || playerScore < dealerScore) {
            return PlayerGameResult.LOSE;
        }

        return PlayerGameResult.PUSH;
    }
}
