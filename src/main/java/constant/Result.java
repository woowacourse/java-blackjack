package constant;

import domain.participant.Dealer;
import domain.participant.Player;

public enum Result {
    LOSE(-1),
    PUSH(0),
    WIN(1),
    BLACKJACK(1.5);

    private final double result;

    Result(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public static Result from(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }

        boolean dealerBlackjack = dealer.isBlackjack();
        boolean playerBlackjack = player.isBlackjack();
        if (dealerBlackjack && !playerBlackjack) {
            return Result.LOSE;
        }
        if (dealerBlackjack && playerBlackjack) {
            return Result.PUSH;
        }
        if (playerBlackjack) {
            return Result.BLACKJACK;
        }

        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();
        if (dealerScore == playerScore) {
            return Result.PUSH;
        }
        if (dealerScore > playerScore) {
            return Result.LOSE;
        }
        return Result.WIN;
    }
}
