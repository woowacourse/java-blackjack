package domain.constant;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.function.IntUnaryOperator;

public enum Result {
    BLACKJACK("블랙잭", bet -> bet + bet / 2),
    WIN("승", bet -> bet),
    DRAW("무", bet -> 0),
    LOSE("패", bet -> bet * -1);

    private final String name;
    private final IntUnaryOperator operator;

    Result(String name, IntUnaryOperator operator) {
        this.name = name;
        this.operator = operator;
    }

    public static Result of(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }

        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        return compareScore(dealer.getScore(), player.getScore());
    }

    public Result reverse() {
        if (this == WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }

    public String getName() {
        return name;
    }

    public int apply(int betAmount) {
        return operator.applyAsInt(betAmount);
    }

    private static Result compareScore(int dealerScore, int playerScore) {
        if (dealerScore < playerScore) {
            return WIN;
        }

        if (dealerScore > playerScore) {
            return LOSE;
        }

        return DRAW;
    }
}
