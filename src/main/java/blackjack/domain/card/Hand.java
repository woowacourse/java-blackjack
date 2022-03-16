package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final String NO_DUPLICATE_CARD_EXCEPTION_MESSAGE = "중복된 카드는 존재할 수 없습니다.";

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(Card card1, Card card2) {
        List<Card> initialCards = new ArrayList<>(List.of(card1, card2));
        return new Hand(initialCards);
    }

    public void add(Card card) {
        validateNoDuplicate(card);
        cards.add(card);
    }

    private void validateNoDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(NO_DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
