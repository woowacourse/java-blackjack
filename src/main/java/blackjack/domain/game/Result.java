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
        int playerScore = player.calculateFinalScore();
        int dealerScore = dealer.calculateFinalScore();

        if (!dealer.isBlackJack() && player.isBlackJack()) {
            return BLACKJACK;
        }

        if (playerScore > dealerScore) {
            return WIN;
        }

        if (player.isBust() || playerScore < dealerScore) {
            return LOSE;
        }

        return DRAW;
    }

    public int calculateProfit(Betting betting) {
        return operator.applyAsInt(betting.getMoney());
    }
}
