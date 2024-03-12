package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {
    private static final int MAX_LIMIT_SCORE = 21;

    private final List<Card> myCards;

    public Hand(List<Card> myCards) {
        this.myCards = new ArrayList<>(myCards);
    }

    public void add(List<Card> cards) {
        myCards.addAll(cards);
    }

    public void add(Card card) {
        myCards.add(card);
    }

    public int compareScoreTo(Hand otherHand) {
        return Integer.compare(findMaximumScore(), otherHand.findMaximumScore());
    }

    // TODO: 간단한 로직으로 수정
    public int findMaximumScore() {
        Set<Integer> scoreCases = new HashSet<>();
        List<List<Integer>> cardsScores = myCards.stream().map(card -> card.score().get()).toList();
        calculateScoreCases(scoreCases, cardsScores, 0, 0);

        return scoreCases.stream()
                .filter(score -> score <= MAX_LIMIT_SCORE)
                .max(Integer::compare)
                .orElse(scoreCases.stream().min(Integer::compare).get());
    }

    private void calculateScoreCases(Set<Integer> scoreCases, List<List<Integer>> cardsScores, int sum, int index) {
        if (index == cardsScores.size()) {
            scoreCases.add(sum);
            return;
        }
        for (int score : cardsScores.get(index)) {
            calculateScoreCases(scoreCases, cardsScores, sum + score, index + 1);
        }
    }

    public void tempMaximumScore() {
        // 만약 에이스가 한번 나오면 다음 에이스는 1로 계산.


    }

    public boolean isOverLimitScore() {
        return findMaximumScore() > MAX_LIMIT_SCORE;
    }

    public boolean isLimitScore() {
        return findMaximumScore() == MAX_LIMIT_SCORE;
    }

    public int getNumberOfCards() {
        return myCards.size();
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public Card getMyCardAt(int index) {
        return myCards.get(index);
    }
}
