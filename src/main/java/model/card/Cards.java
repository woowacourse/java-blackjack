package model.card;

import static constant.BlackjackConstant.BUST_NUMBER;

import java.util.ArrayList;
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
        generateCandidates(candidates, totalWithoutAce(), 0, aceCount());

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

    private void generateCandidates(Set<Integer> candidates, int currentSum, int usedAces, int totalAces) {
        if (usedAces == totalAces) {
            candidates.add(currentSum);
            return;
        }

        generateCandidates(candidates, currentSum + 1, usedAces + 1, totalAces);
        generateCandidates(candidates, currentSum + 11, usedAces + 1, totalAces);
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return cards;
    }
}
