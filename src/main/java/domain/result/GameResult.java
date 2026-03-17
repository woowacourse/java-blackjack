package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResult {
    BLACKJACK("블랙잭", 3, 2),
    WIN("승", 1, 1),
    DRAW("무", 0, 1),
    LOSE("패", -1, 1);

    private final String description;
    private final int numerator;
    private final int denominator;

    GameResult(final String description, final int numerator, final int denominator) {
        this.description = description;
        this.numerator = numerator;
        this.denominator = denominator;
    }


    public int calculateBetProfit(final int betAmount) {
        return betAmount * numerator / denominator;
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
