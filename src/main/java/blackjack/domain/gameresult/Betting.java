package blackjack.domain.gameresult;

import java.util.Objects;

public class Betting {
    private static final int MIN_BET = 1;
    private static final int MAX_BET = 10_000_000;

    private final double bet;

    private Betting(double bet) {
        this.bet = bet;
    }

    public static Betting from(double bat) {
        validateBet(bat);
        return new Betting(bat);
    }

    private static void validateBet(double bat) {
        if (bat < MIN_BET || bat > MAX_BET) {
            throw new IllegalArgumentException("배팅은 최소 " + MIN_BET + "부터 "
                    + MAX_BET + "까지 가능합니다.");
        }
    }

    public double getBet() {
        return bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Betting betting = (Betting) o;
        return Objects.equals(bet, betting.bet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet);
    }
}
