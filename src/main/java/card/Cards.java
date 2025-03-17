package card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {

    private static final int BUST_NUMBER = 21;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateNearestTotal() {
        Set<Integer> candidates = generateCandidates(totalWithoutAce(), aceCount());

        return candidates.stream()
                .filter(candidate -> candidate <= BUST_NUMBER)
                .max(Integer::compareTo)
                .orElseGet(() -> Collections.min(candidates));
    }

    private int totalWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isSameCardNumber(CardNumber.ACE))
                .mapToInt(Card::getCardNumberValue)
                .sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(card -> card.isSameCardNumber(CardNumber.ACE))
                .count();
    }

    private Set<Integer> generateCandidates(int initialSum, int totalAces) {
        record State(int sum, int usedAces) {
        }

        Set<Integer> candidates = new HashSet<>();
        Deque<State> deque = new ArrayDeque<>();
        deque.push(new State(initialSum, 0));

        while (!deque.isEmpty()) {
            State state = deque.pop();
            int currentSum = state.sum();
            int usedAces = state.usedAces();

            if (usedAces == totalAces) {
                candidates.add(currentSum);
                continue;
            }

            deque.push(new State(currentSum + 1, usedAces + 1));
            deque.push(new State(currentSum + 11, usedAces + 1));
        }

        return candidates;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
