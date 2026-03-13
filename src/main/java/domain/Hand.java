package domain;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public class Hand {
    public static final int BLACKJACK_SCORE = 21;
    private final Cards cards;

    public Hand() {
        cards = new Cards();
    }

    public Hand(Cards cards) {
        this.cards = cards;
    }

    public int getSum() {
        return cards.getSum();
    }

    public boolean isBust() {
        return getSum() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return getSum() == BLACKJACK_SCORE;
    }

    public Hand addCard(Card card) {
        return new Hand(cards.addCard(card));
    }

    public Cards getCards() {
        return cards;
    }

    public List<Card> getCardsList() {
        return cards.cards();
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public int size() {
        return cards.size();
    }
}
