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
        Result blackjackResult = determineByBlackjack(dealer, player);
        if (blackjackResult != null) {
            return blackjackResult;
        }
        return determineByScore(dealer, player);
    }

    private static Result determineByBlackjack(Dealer dealer, Player player) {
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return Result.LOSE;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return Result.PUSH;
        }
        if (player.isBlackjack()) {
            return Result.BLACKJACK;
        }
        return null; // blackjack 여부로 결정을 할 수 없을 때, null을 발생시킵니다.
    }

    private static Result determineByScore(Dealer dealer, Player player) {
        if (dealer.calculateScore() == player.calculateScore()) {
            return Result.PUSH;
        }
        if (dealer.calculateScore() > player.calculateScore()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }
}
