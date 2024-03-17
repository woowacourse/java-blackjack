package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class Judge {

    private final Dealer dealer;
    private final Player player;

    public Judge(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
    }

    public GameResult judge() {
        if (dealer.isBust()) {
            return judgeWhenDealerBust();
        }

        if (dealer.isBlackjack()) {
            return judgeWhenDealerBlackjack();
        }

        return judgeWhenDealerNormal();
    }

    private GameResult judgeWhenDealerBust() {
        if (player.isBust()) {
            return GameResult.DRAW;
        }

        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }

        return GameResult.WIN;
    }

    private GameResult judgeWhenDealerBlackjack() {
        if (player.isBlackjack()) {
            return GameResult.DRAW;
        }

        return GameResult.LOSE;
    }

    private GameResult judgeWhenDealerNormal() {
        if (player.isBust()) {
            return GameResult.LOSE;
        }

        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }

        return judgeWhenNormalTogether();
    }

    private GameResult judgeWhenNormalTogether() {
        if (dealer.isSameScore(player.getScore())) {
            return GameResult.DRAW;
        }

        if (dealer.getScore() > player.getScore()) {
            return GameResult.LOSE;
        }

        return GameResult.WIN;
    }
}
