package domain;

import domain.card.Card;
import domain.card.Cards;

import java.util.Collections;
import java.util.List;

public class Dealer {

    private static final int STAY_LOWER_BOUND = 16;
    private static final int BLACKJACK = 21;

    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isStay() {
        return calculateScore() > STAY_LOWER_BOUND;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.toList());
    }
}
