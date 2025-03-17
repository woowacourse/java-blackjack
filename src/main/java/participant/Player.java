package participant;

import card.Card;
import hand.Hand;
import java.util.Objects;
import participant.value.Money;

public class Player extends Participant {
    private final String name;
    private final Money bettingPrice;

    public Player(String name, Hand hand, Money bettingPrice) {
        super(hand);
        this.name = name;
        this.bettingPrice = bettingPrice;
    }

    public Player(String name, Money bettingPrice) {
        super(Hand.createEmpty());
        this.name = name;
        this.bettingPrice = bettingPrice;
    }

    public Player(String name) {
        super(Hand.createEmpty());
        this.name = name;
        this.bettingPrice = Money.ZERO;
    }

    public static Player withBet(String name, Money bettingPrice) {
        return new Player(name, bettingPrice);
    }

    public String getName() {
        return name;
    }

    public Money calculateProfit(double payoutRate) {
        return Money.multiply(bettingPrice, payoutRate);
    }

    @Override
    public Hand openHand() {
        return hand;
    }

    @Override
    public Participant initializeHandWith(Hand updatedHand) {
        return new Player(this.getName(), updatedHand, this.bettingPrice);
    }

    @Override
    public Participant updateHandWith(Card card) {
        return new Player(this.getName(), hand.add(card), this.bettingPrice);
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
