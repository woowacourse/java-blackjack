package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int MAXIMUM_SUM = 21;
    private static final int BLACKJACK_SUM = 21;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void openCards(int count) {
        while (count > 0) {
            Card willBeOpened = findNotOpenedCard();
            willBeOpened.open();
            count--;
        }
    }

    private Card findNotOpenedCard() {
        return cards.stream()
                .filter(card -> !card.isOpened())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("오픈할 카드가 없습니다."));
    }

    public List<Card> getOpenedCards() {
        return cards.stream()
                .filter(Card::isOpened)
                .toList();
    }

    public boolean isBust() {
        return computeOptimalSum() > MAXIMUM_SUM;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && computeOptimalSum() == BLACKJACK_SUM;
    }

    public int computeOptimalSum() {
        List<Integer> candidates = new ArrayList<>();
        computeCandidates(0, 0, cards, candidates);

        return candidates.stream()
                .filter(sum -> sum <= MAXIMUM_SUM)
                .max(Integer::compareTo)
                .orElseGet(() -> candidates.stream()
                        .filter(sum -> sum > MAXIMUM_SUM)
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
