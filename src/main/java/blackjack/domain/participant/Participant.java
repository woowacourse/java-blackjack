package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import java.util.List;

public abstract class Participant {

    protected final Name name;
    protected final Hand cardHand;

    protected Participant(Name name, Hand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public Hand getCardHand() {
        return cardHand;
    }
}
