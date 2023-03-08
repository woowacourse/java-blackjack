package domain;

import domain.card.Card;
import domain.card.Denomination;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int ADDABLE_POINT = 10;
    private static final int BLACK_JACK_POINT = 21;
    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand valueOf(List<Card> cards) {
        if (cards == null) {
            return new Hand(new ArrayList<>());
        }
        return new Hand(cards);
    }

    public Hand add(Card card) {
        List<Card> newHandCards = new ArrayList<>(cards);
        newHandCards.add(card);
        return new Hand(newHandCards);
    }

    public List<Card> getAllCards() {
        return List.copyOf(cards);
    }

    public int calculatePoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getDenomination().getPoint();
        }
        if (hasAce()) {
            return calculateAce(point);
        }
        return point;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch((card) -> card.getDenomination() == Denomination.ACE);
    }

    private int calculateAce(int point) {
        if (point + ADDABLE_POINT <= BLACK_JACK_POINT) {
            return point + ADDABLE_POINT;
        }
        return point;
    }

    public int getSize() {
        return cards.size();
    }

    @Override
    public String toString() {
        return "Hand{" +
            "cards=" + cards +
            '}';
    }
}
