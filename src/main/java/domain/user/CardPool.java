package domain.user;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;

public class CardPool {

    private static final int CARD_POINT_LIMIT = 21;

    private final List<Card> cards;

    public CardPool(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int sumCardNumbers() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getNumber().getPoint();
        }

        if (sum > CARD_POINT_LIMIT && containsAce()) {
            sum -= CARD_POINT_LIMIT - CardNumber.ACE.getPoint();
        }
        return sum;
    }

    public boolean isOverCardPointLimit() {
        return sumCardNumbers() > CARD_POINT_LIMIT;
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(card -> card.getNumber() == CardNumber.ACE);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
