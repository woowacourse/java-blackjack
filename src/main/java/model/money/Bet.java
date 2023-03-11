package model.money;

import java.util.Objects;
import model.user.GameState;

public class Bet {

    private static final double MULTIPLE_VALUE = 1.5;
    private static final Bet zero = new Bet(0);

    private final long money;

    public Bet(final long money) {
        this.money = money;
    }

    public Bet(String bet) {
        this(Long.parseLong(bet));
    }

    public static Bet zero() {
        return zero;
    }

    public Bet add(final Bet bet) {
        return new Bet(this.money + bet.money);
    }

    public Bet calculateBet(final GameState gameState) {
        if (gameState.isBlackJack()) {
            return blackJack();
        }

        return judgeFinalBet(gameState);
    }

    private Bet judgeFinalBet(final GameState gameState) {
        if (gameState.isWin()) {
            return this;
        }

        if (gameState.isLose()) {
            return lose();
        }

        return draw();
    }

    private Bet lose() {
        return new Bet(-money);
    }

    private Bet draw() {
        return zero;
    }

    public Bet blackJack() {
        return new Bet((long) (money * MULTIPLE_VALUE));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Bet bet1 = (Bet) o;
        return Objects.equals(money, bet1.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                '}';
    }

    public Long getMoney() {
        return money;
    }
}
