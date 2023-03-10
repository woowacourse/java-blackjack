package model.money;

import static model.card.State.*;

import java.util.Objects;
import model.card.State;

public class Bet {

    private static final double MULTIPLE_VALUE = 1.5;
    private static final Bet zero = new Bet(0);

    private final long money;

    public Bet(final long money) {
        this.money = money;
    }

    public static Bet zero() {
        return zero;
    }

    public Bet add(final Bet bet) {
        return new Bet(this.money + bet.money);
    }

    public Bet calculateMoney(State result) {
        if (result == BLACKJACK) {
            return blackJack();
        }

        return judge(result);
    }

    private Bet judge(State result) {
        if (result == WIN) {
            return this;
        }

        if (result == LOSE) {
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
}
