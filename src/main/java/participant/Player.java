package participant;

import card.Hand;
import java.util.Objects;

public class Player extends Participant {
    private final String name;
    private final Money bettingPrice;

    public Player(String name, Hand hand, Money bettingPrice) {
        super(hand);
        this.name = name;
        this.bettingPrice = bettingPrice;
    }

    public String getName() {
        return name;
    }

    public Money calculateProfit(double payoutRate) {
        return Money.multiply(bettingPrice, payoutRate);
    }

    @Override
    public Hand openInitialHand() {
        return hand;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
