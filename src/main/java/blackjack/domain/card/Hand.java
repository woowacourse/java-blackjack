package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private static final String NO_DUPLICATE_CARD_EXCEPTION_MESSAGE = "중복된 카드는 존재할 수 없습니다.";
    private static final int INITIAL_CARDS_COUNT = 2;

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(Card card1, Card card2) {
        List<Card> initialCards = new ArrayList<>(List.of(card1, card2));
        return new Hand(initialCards);
    }

    public List<Card> getInitialCards() {
        return cards.subList(0, INITIAL_CARDS_COUNT);
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

    public List<String> generateCardNames() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hand hand = (Hand) o;

        return cards.equals(hand.cards);
    }

    @Override
    public int hashCode() {
        return cards.hashCode();
    }
}
