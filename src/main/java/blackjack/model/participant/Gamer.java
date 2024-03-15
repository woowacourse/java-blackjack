package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public abstract class Gamer {

    protected final Name name;
    protected Hand hand;

    protected Gamer(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void receiveCard(final Card card) {
        this.hand = hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public List<Card> openCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public int getCardCount() {
        return hand.countSize();
    }

    public Name getName() {
        return name;
    }

    public abstract boolean canHit();
}
