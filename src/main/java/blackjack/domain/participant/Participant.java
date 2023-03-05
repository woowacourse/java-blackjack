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

    public void combat(Participant other) {
        boolean isBust = isBust();
        boolean isOtherBust = other.isBust();
        int score = calculateScore();
        int otherScore = other.calculateScore();
        if (!isBust && (isOtherBust || score > otherScore)) {
            this.win();
            other.lose();
        }
        if (!isOtherBust && (isBust || score < otherScore)) {
            this.lose();
            other.win();
        }
        if (!isBust && (score == otherScore)) {
            this.tie();
            other.tie();
        }
    }

    public abstract void win();

    public abstract void lose();

    public abstract void tie();
}
