package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Cards {
    private static final int BUST_LIMIT = 21;

    private final List<Card> cards;
    private final Map<Card, Boolean> isOpened;


    public Cards() {
        this.cards = new ArrayList<>();
        this.isOpened = new HashMap<>();
    }

    public void addAll(List<Card> cards) {
        cards.forEach(card -> isOpened.put(card, false));
        this.cards.addAll(cards);
    }

    public void openCards(int count) {
        while (count > 0) {
            Card willBeOpened = findNotOpenedCard();
            isOpened.put(willBeOpened, true);
            count--;
        }
    }

    private Card findNotOpenedCard() {
        return cards.stream()
                .filter(card -> !isOpened.get(card))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("오픈할 카드가 없습니다."));
    }

    public List<Card> getOpenedCards() {
        return isOpened.entrySet()
                .stream()
                .filter(Entry::getValue)
                .map(Map.Entry::getKey)
                .toList();
    }

    public boolean isBust() {
        return computeOptimalSum() > BUST_LIMIT;
    }

    public int computeOptimalSum() {
        List<Integer> candidates = new ArrayList<>();
        computeCandidates(0, 0, cards, candidates);

        return candidates.stream()
                .filter(sum -> sum <= BUST_LIMIT)
                .max(Integer::compareTo)
                .orElseGet(() -> candidates.stream()
                        .filter(sum -> sum > BUST_LIMIT)
                        .min(Integer::compareTo)
                        .orElseThrow(() -> new IllegalStateException("논리적으로 도달할 수 없는 예외입니다.")));
    }

    private void computeCandidates(int cardIndex, int sum, List<Card> cards, List<Integer> candidates) {
        if (cardIndex == cards.size()) {
            candidates.add(sum);
            return;
        }

        for (int score : cards.get(cardIndex).getScores()) {
            computeCandidates(cardIndex + 1, sum + score, cards, candidates);
        }
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
