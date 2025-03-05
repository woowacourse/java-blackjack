package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cards {
    private static final int BURST_LIMIT = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int size() {
        return cards.size();
    }

    public int calculateOptimalSum() {
        List<Integer> candidates = new ArrayList<>();
        calculateCandidates(0, 0, cards, candidates);

        Optional<Integer> maxUnder21 = candidates.stream()
                .filter(sum -> sum <= BURST_LIMIT)
                .max(Integer::compareTo);
        return maxUnder21.orElseGet(() -> candidates.stream()
                .filter(sum -> sum > BURST_LIMIT)
                .min(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("논리적으로 도달할 수 없는 예외입니다.")));
    }

    public boolean isBurst() {
        return calculateOptimalSum() > BURST_LIMIT;
    }

    private void calculateCandidates(int cardIndex, int sum, List<Card> cards, List<Integer> candidates) {
        if (cardIndex == cards.size()) {
            candidates.add(sum);
            return;
        }

        for (int score : cards.get(cardIndex).getScores()) {
            calculateCandidates(cardIndex + 1, sum + score, cards, candidates);
        }
    }
}
