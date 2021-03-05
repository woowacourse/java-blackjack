package blackjack.domain.participant;

import static blackjack.domain.game.Rule.THRESHOLD;

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

    public List<Card> getCardHand() {
        return cardHand.getCards();
    }

    public abstract int getCardSum();

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return getCardSum() > THRESHOLD;
    }
}
