package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {
    protected final Hand hand;
    protected final Name name;

    public Participant(final String name) {
        this.hand = new Hand();
        this.name = new Name(name);
    }

    public void addCard(final Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public abstract boolean isDrawable();

    public int getScore() {
        return hand.calculateScore();
    }

    public List<Card> getCards() {
        return hand.getAllCards();
    }

    public String getName() {
        return name.getValue();
    }
}
