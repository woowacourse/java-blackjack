package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Hand {

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand empty() {
        return new Hand(new ArrayList<>());
    }

    public static Hand of(List<Card> cards) {
        return new Hand(cards);
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public Set<Integer> getCoordinateSums() {
        return getCoordinateSumsByDfs(0, 0, new HashSet<>());
    }

    private Set<Integer> getCoordinateSumsByDfs(int index, int sum, Set<Integer> result) {
        if (index == cards.size()) {
            result.add(sum);
            return result;
        }

        Card card = cards.get(index);
        for (int coordinateNumber : card.getCoordinateNumbers()) {
            result = getCoordinateSumsByDfs(index + 1, sum + coordinateNumber, result);
        }
        return result;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
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
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
