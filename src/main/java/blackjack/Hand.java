package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {
    private final List<Card> cards;

    public Hand(Card card1, Card card2) {
        this.cards = new ArrayList<>(List.of(card1, card2));
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card newCard) {
        cards.add(newCard);
    }

    public List<Integer> getPossibleSums() {
        Set<Integer> possibleSums = new HashSet<>();

        calculatePossibleSums(possibleSums, 0, 0);

        return possibleSums.stream().toList();
    }

    public void calculatePossibleSums(Set<Integer> values, int index, int sum) {
        if (cards.size() == index) {
            values.add(sum);
            return;
        }

        Card card = cards.get(index);
        for (int number : card.getValue()) {
            calculatePossibleSums(values, index + 1, sum + number);
        }
    }
}
