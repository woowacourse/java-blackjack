package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Participant {

    protected final Name name;
    protected final Hand cardHand;

    public Participant(Name name, Hand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public String getName() {
        return name.getName();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }

    public int getScore() {
        return cardHand.getScore();
    }
}
