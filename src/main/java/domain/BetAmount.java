package domain;

import domain.result.Status;

import java.util.Objects;

public class BetAmount {

    private final int betAmount;

    public BetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public BetAmount(String betAmount) {
        this.betAmount = Integer.parseInt(betAmount);
    }

    public int determineIncome(Status status) {
        if (status == Status.WIN) {
            return betAmount;
        }
        if (status == Status.LOSE) {
            return -betAmount;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetAmount betAmount1 = (BetAmount) o;
        return betAmount == betAmount1.betAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betAmount);
    }

    @Override
    public String toString() {
        return "BetAmount{" +
                "betAmount=" + betAmount +
                '}';
    }
}
