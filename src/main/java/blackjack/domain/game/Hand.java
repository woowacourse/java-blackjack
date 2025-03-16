package blackjack.domain.game;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {

    public static final int BUSTED_STANDARD_VALUE = 22;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }

    public void takeCard(Card newCard) {
        cards.add(newCard);
    }

    public List<Integer> calculatePossibleSums() {
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
        for (int number : card.getRankValues()) {
            calculatePossibleSums(values, index + 1, sum + number);
        }
    }

    public int getOptimisticValue() {
        return calculatePossibleSums().stream()
                .filter(sum -> sum < BUSTED_STANDARD_VALUE)
                .max(Comparator.naturalOrder())
                .orElse(BUSTED_STANDARD_VALUE);
    }
}
