package blackjack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {
    //    public static final int HIT_UPPER_BOUND = 16;
    private static final int MAX_LIMIT_SCORE = 21;

    private final List<Card> myCards;

    public Hand(List<Card> myCards) {
        this.myCards = myCards;
    }

    public void addCard(Card card) {
        myCards.add(card);
    }

    public void addCards(List<Card> cards) {
        myCards.addAll(cards);
    }

    public int compareScoreTo(Hand otherHand) {
        return Integer.compare(findMaximumScore(), otherHand.findMaximumScore());
    }

    // TODO: 간단한 로직으로 수정
    public int findMaximumScore() {
        Set<Integer> scoreCases = new HashSet<>();
        List<List<Integer>> cardsScores = myCards.stream().map(card -> card.getScore().get()).toList();
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
