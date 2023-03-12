package blackjack.domain;

import java.text.DecimalFormat;

public class Bet {
    private static final int MINIMUM_BET = 0;
    private static final int MAXIMUM_BET = 100_000_000;
    private static final DecimalFormat decimalFormat = new DecimalFormat("###,###");

    private final int bet;

    private Bet(int bet) {
        this.bet = bet;
    }

    public static Bet of(int bet) {
        validateBet(bet);
        return new Bet(bet);
    }

    private static void validateBet(int bet) {
        if (bet <= MINIMUM_BET || bet >= MAXIMUM_BET) {
            throw new IllegalArgumentException(
                    "[ERROR] 배팅금은 " + decimalFormat.format(MINIMUM_BET) + "보다 크거나 " + decimalFormat.format(MAXIMUM_BET)
                            + "보다 작아야 합니다.");
        }
    }

    public Bet calculateResult(GameResult gameResult) {
        return new Bet(gameResult.applyBet(bet));
    }

    public int getBet() {
        return bet;
    }
}
