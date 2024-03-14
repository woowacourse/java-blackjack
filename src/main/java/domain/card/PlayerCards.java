package domain.card;

import domain.BetAmount;
import domain.Name;
import domain.result.Income;
import domain.result.Status;

import java.util.List;
import java.util.Objects;

public class PlayerCards extends Cards {

    private final Name name;
    private final BetAmount betAmount;

    public PlayerCards(Name name, BetAmount betAmount, List<Card> cards) {
        super(cards);
        this.name = name;
        this.betAmount = betAmount;
    }

    public Income determineIncome(Status status) {
        return betAmount.determineIncome(status);
    }

    @Override
    public boolean canDraw() {
        return bestSum() <= MAX_SCORE;
    }

    public Name getPlayerName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayerCards that = (PlayerCards) o;
        return Objects.equals(name, that.name) && Objects.equals(betAmount, that.betAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, betAmount);
    }

    @Override
    public String toString() {
        return "PlayerCards{" +
                "name=" + name +
                ", betAmount=" + betAmount +
                '}';
    }
}
