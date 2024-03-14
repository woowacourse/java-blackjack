package domain.player;

import domain.score.Revenue;

import java.util.Objects;

public class Bet {

    private static final int MIN_AMOUNT = 1;
    
    private final int amount;

    public Bet(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("최소 베팅 금액은 1원입니다.");
        }
    }

    public Revenue lose() {
        return new Revenue(amount * -1);
    }

    public Revenue win() {
        return new Revenue(amount);
    }

    public Revenue stay() {
        return new Revenue(0);
    }

    public Revenue multiply() {
        return new Revenue(amount * 1.5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet revenue = (Bet) o;
        return amount == revenue.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public int getAmount() {
        return amount;
    }
}
