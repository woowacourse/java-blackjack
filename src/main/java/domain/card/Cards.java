package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BUST_THRESHOLD = 21;
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int computeOptimalSum() {
        List<Integer> candidates = new ArrayList<>();
        computeCandidates(0, 0, cards, candidates);

        return candidates.stream()
                .filter(sum -> sum <= BUST_THRESHOLD)
                .max(Integer::compareTo)
                .orElseGet(() -> candidates.stream()
                        .filter(sum -> sum > BUST_THRESHOLD)
                        .min(Integer::compareTo)
                        .orElseThrow(() -> new IllegalStateException("합을 계산할 카드가 없습니다.")));
    }

    private void computeCandidates(int cardIndex, int sum, List<Card> cards, List<Integer> candidates) {
        if (cardIndex == cards.size()) {
            candidates.add(sum);
            return;
        }

        for (int score : cards.get(cardIndex).scores()) {
            computeCandidates(cardIndex + 1, sum + score, cards, candidates);
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }
}
