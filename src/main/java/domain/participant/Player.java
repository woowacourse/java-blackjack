package domain.participant;

import domain.GameResult;
import domain.card.Hand;
import java.util.Objects;

public class Player extends Participant {

    private final String name;
    private final Money bettingAmount;

    private Player(Hand hand, String name, Money bettingAmount) {
        super(hand);
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    public static Player init(String name, Money bettingAmount) {
        return new Player(Hand.empty(), name, bettingAmount);
    }

    public static Player of(Hand hand, String name, Money bettingAmount) {
        return new Player(hand, name, bettingAmount);
    }

    public Money calculateRevenue(GameResult result) {
        return result.applyReturnRate(bettingAmount);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(bettingAmount, player.bettingAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
