package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.Collections;
import java.util.List;

abstract class Participant {

    private static final int BLACKJACK = 21;

    private final Cards cards;

    Participant() {
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.getScore();
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.toList());
    }
}
