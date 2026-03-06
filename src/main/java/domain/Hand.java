package domain;

import java.util.Collections;
import java.util.List;
import util.ErrorMessage;

public class Hand {
    public static final int ACE_PROFIT_VALUE = 10;
    private static final int BLACKJACK_MAX_SCORE = 21;
    private static final int MIN_SIZE = 2;
    private static final int MIN_TOTAL_SCORE = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        validateCardsSize(cards);
        this.cards = cards;
    }

    // 버스트 확인
    public static boolean isBurst(int totalScore) {
        if (totalScore < MIN_TOTAL_SCORE) {
            throw new IllegalArgumentException(ErrorMessage.BURST_TOTAL_SCORE.getMessage());
        }
        return totalScore > BLACKJACK_MAX_SCORE;
    }

    private void validateCardsSize(List<Card> cards) { //테스트 작성
        if (cards.size() != MIN_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.HANDS_CARDS_SIZE.getMessage());
        }
    }

    public int getTotalScore() {    // 테스트
        int totalScore = cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .orElse(0);

        if (totalScore <= BLACKJACK_MAX_SCORE - ACE_PROFIT_VALUE && hasAce(cards)) {
            return totalScore + ACE_PROFIT_VALUE;
        }

        return totalScore;
    }

    public int getHandsSize() {
        return cards.size();
    }

    public void add(Card card) {
        cards.add(card);
    }

    private boolean hasAce(List<Card> hands) {
        return hands.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
