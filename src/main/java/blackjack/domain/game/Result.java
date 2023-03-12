package blackjack.domain.game;


import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.function.IntUnaryOperator;

public enum Result {

    BLACKJACK(betting -> (int) (betting * 1.5)),
    WIN(betting -> betting),
    LOSE(betting -> betting * -1),
    DRAW(betting -> 0);

    private final IntUnaryOperator operator;
    Result(IntUnaryOperator operator) {
        this.operator = operator;
    }

    public static Result calculatePlayerResult(Player player, Dealer dealer) {
        int playerScore = player.calculateCurrentScore();
        int dealerScore = dealer.calculateCurrentScore();

        if (player.isBlackJack()) {
            return checkBlackJack(dealer);
        }

        if (player.isBust()) {
            return LOSE;
        }

        if (dealer.isBust() || playerScore > dealerScore) {
            return WIN;
        }

        if (dealerScore > playerScore) {
            return LOSE;
        }
        return DRAW;
    }

    private static Result checkBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return DRAW;
        }
        return BLACKJACK;
    }

    public int calculateProfit(Betting betting) {
        return operator.applyAsInt(betting.getMoney());
    }
}
