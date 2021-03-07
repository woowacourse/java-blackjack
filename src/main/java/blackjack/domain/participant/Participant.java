package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Participant {

    protected final String name;
    protected final Hand cardHand;

    public Participant(String name, Hand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public String getName() {
        return name;
    }

    public abstract int getHandTotal();

    public abstract boolean isBust();
}
