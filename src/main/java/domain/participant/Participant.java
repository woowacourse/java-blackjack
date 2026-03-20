package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public abstract class Participant {
    private final Cards cards;

    public Participant(Cards cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public List<Card> getHand() {
        return cards.getHand();
    }
}
