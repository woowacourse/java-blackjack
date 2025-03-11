package model.card;

import static constant.BlackjackConstant.BUST_NUMBER;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateNearestTotal() {
        Set<Integer> candidates = new HashSet<>();
        generateCandidates(candidates, totalWithoutAce(), aceCount());

        return candidates.stream()
                .filter(candidate -> candidate <= BUST_NUMBER)
                .max(Integer::compareTo)
                .orElse(candidates.stream()
                        .min(Integer::compareTo)
                        .get());
    }

    private int totalWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(card -> card.getCardNumber().getValues().getFirst())
                .sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private void generateCandidates(Set<Integer> candidates, int initialSum, int totalAces) {
        record State(int sum, int usedAces) {}

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
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
