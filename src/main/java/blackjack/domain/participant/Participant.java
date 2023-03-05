package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;

public abstract class Participant {
    protected final Name name;
    protected final Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public void hit(List<Card> cards) {
        cards.forEach(hand::add);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.calculateScore() >= 22;
    }

    public Name getName() {
        return name;
    }

    public abstract void win();

    public abstract void lose();

    public abstract void tie();
}
