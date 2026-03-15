package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

import static domain.BlackjackRule.BLACK_JACK;
import static domain.BlackjackRule.INITIAL_CARDS_COUNT;

public class Hand {
    public static final int ACE_ADJUST_VALUE = 10;

    private final Cards cards;

    public Hand() {
        this.cards = new Cards();
    }

    public Hand(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    public boolean isBust() {
        return score() > BLACK_JACK;
    }

    public boolean isBlackjack() {
        return score() == BLACK_JACK && size() == INITIAL_CARDS_COUNT;
    }

    public int score() {
        int total = cards.sum();
        int aceCount = cards.countAce();

        while (total > BLACK_JACK && aceCount > 0) {
            total -= ACE_ADJUST_VALUE;
            aceCount--;
        }

        return total;
    }

    public Card peek() {
        return cards.peek();
    }

    public int size() {
        return cards.size();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return cards.cards();
    }
}
