package blackjack.domain;

import blackjack.common.Constants;
import blackjack.common.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {

    private static final int BLACKJACK_SUM = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(Card card1, Card card2) {
        validate(card1, card2);
        List<Card> cards = new ArrayList<>(List.of(card1, card2));
        return new Hand(cards);
    }

    private static void validate(Card card1, Card card2) {
        if (card1 == null || card2 == null) {
            throw new IllegalArgumentException(ErrorMessage.CAN_NOT_NULL.getMessage());
        }
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }

    public void takeCard(Card newCard) {
        cards.add(newCard);
    }

    public int getBestCardValue() {
        return getPossibleSums().stream()
                .filter(sum -> sum <= Constants.BUSTED_STANDARD_VALUE)
                .max(Comparator.naturalOrder())
                .orElse(Constants.BUSTED_VALUE);
    }

    public Card getCard(int position) {
        if (position < 0 || position >= cards.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CARD_INDEX.getMessage());
        }

        return cards.get(position);
    }

    public boolean isBusted() {
        List<Integer> possibleSums = getPossibleSums();

        return possibleSums.stream()
                .allMatch(sum -> sum > Constants.BUSTED_STANDARD_VALUE);
    }

    public boolean canTakeCardWithin(int takeBoundary) {
        List<Integer> possibleSums = getPossibleSums();

        return possibleSums.stream()
                .anyMatch(sum -> sum <= takeBoundary);
    }

    private List<Integer> getPossibleSums() {
        Set<Integer> possibleSums = new HashSet<>();

        calculatePossibleSums(possibleSums, 0, 0);
        return possibleSums.stream()
                .toList();
    }

    private void calculatePossibleSums(Set<Integer> values, int index, int sum) {
        if (cards.size() == index) {
            values.add(sum);
            return;
        }

        Card card = cards.get(index);
        for (int number : card.getValue()) {
            calculatePossibleSums(values, index + 1, sum + number);
        }
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_SIZE && getBestCardValue() == BLACKJACK_SUM;
    }
}
