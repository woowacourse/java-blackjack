package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

public final class BlackjackJudge {

    public PlayerResult judgePlayerResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return PlayerResult.LOSE;
        }
        if (dealer.isBust()) {
            return PlayerResult.WIN;
        }

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return PlayerResult.DRAW;
        }
        if (player.isBlackjack()) {
            return PlayerResult.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return PlayerResult.LOSE;
        }

        return judgeScore(dealer, player);
    }

    private PlayerResult judgeScore(Dealer dealer, Player player) {
        if (player.calculateScore() > dealer.calculateScore()) {
            return PlayerResult.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}
