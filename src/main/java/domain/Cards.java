package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BURST_LIMIT = 21;
    public static int ans = 0;
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
        return Collections.max(candidates);
    }

    private void calculateCandidates(int cardIndex, int sum, List<Card> cards, List<Integer> candidates) {
        if (sum > BURST_LIMIT) {
            return;
        }
        if (cardIndex == cards.size()) {
            candidates.add(sum);
            return;
        }

        for (int score : cards.get(cardIndex).getScores()) {
            calculateCandidates(cardIndex + 1, sum + score, cards, candidates);
        }
    }
}
