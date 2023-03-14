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
        if (isPlayerLose(player, dealer)) {
            return LOSE;
        }

        if (isPlayerWinOrBlackJack(player, dealer)) {
            return checkBlackJack(player, dealer);
        }
        return DRAW;
    }

    private static boolean isPlayerLose(Player player, Dealer dealer) {
        return player.isBust() || dealer.isHigherThan(player) && !dealer.isBust();
    }

    private static boolean isPlayerWinOrBlackJack(Player player, Dealer dealer) {
        return dealer.isBust() || player.isHigherThan(dealer) && !player.isBust();
    }

    private static Result checkBlackJack(Player player, Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BLACKJACK;
        }
        return WIN;
    }

    public int calculateProfit(BettingMoney bettingMoney) {
        return operator.applyAsInt(bettingMoney.getValue());
    }
}
