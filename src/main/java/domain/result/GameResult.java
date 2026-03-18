package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import java.math.BigDecimal;

public enum GameResult {
    BLACKJACK("블랙잭", new BigDecimal("1.5")),
    WIN("승", BigDecimal.ONE),
    DRAW("무", BigDecimal.ZERO),
    LOSE("패", new BigDecimal("-1"));

    private final String description;
    private final BigDecimal multiplier;

    GameResult(final String description, final BigDecimal multiplier) {
        this.description = description;
        this.multiplier = multiplier;
    }


    public int calculateBetProfit(final int betAmount) {
        return multiplier
                .multiply(BigDecimal.valueOf(betAmount))
                .intValue();
    }

    public static GameResult judge(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return LOSE;
        }
        return compareScore(dealer, player);
    }

    private static GameResult compareScore(final Dealer dealer, final Player player) {
        final int dealerScore = dealer.getScore();
        final int playerScore = player.getScore();

        if (dealerScore < playerScore) {
            return WIN;
        }
        if (dealerScore > playerScore) {
            return LOSE;
        }
        return DRAW;
    }
}
