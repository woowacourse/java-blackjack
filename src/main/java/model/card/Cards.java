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
        Deque<List<Integer>> deque = new ArrayDeque<>();
        deque.push(new ArrayList<>(List.of(initialSum, 0)));

        while (!deque.isEmpty()) {
            List<Integer> state = deque.pop();
            Integer currentSum = state.get(0);
            Integer usedAces = state.get(1);

            if (usedAces == totalAces) {
                candidates.add(currentSum);
                continue;
            }

            deque.push(new ArrayList<>(List.of(currentSum + 1, usedAces + 1)));
            deque.push(new ArrayList<>(List.of(currentSum + 11, usedAces + 1)));
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
