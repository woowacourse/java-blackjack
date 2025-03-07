package blackjack.domain;

import blackjack.common.Constants;
import blackjack.common.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand implements CardHolder {
    // TODO 해당 로직을 참여자에 추가해도 될 거 같다.

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

    public List<Integer> getPossibleSums() {
        Set<Integer> possibleSums = new HashSet<>();

        calculatePossibleSums(possibleSums, 0, 0);
        return possibleSums.stream().toList();
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

    public int getOptimisticValue() {
        return getPossibleSums().stream().filter(sum -> sum <= Constants.BUSTED_STANDARD_VALUE)
                .max(Comparator.naturalOrder())
                .orElse(Constants.BUSTED_VALUE);
    }
}
