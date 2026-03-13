package domain.player;

import domain.card.Card;
import java.util.Collections;
import java.util.List;
import util.ErrorMessage;

public class Hand {
    public static final int ACE_PROFIT_VALUE = 10;
    private static final int BLACKJACK_MAX_SCORE = 21;
    private static final int MIN_SIZE = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        validateCardsSize(cards);
        this.cards = cards;
    }

    public boolean isBust() {
        return calculateTotalScore() > BLACKJACK_MAX_SCORE;
    }

    private void validateCardsSize(List<Card> cards) { //테스트 작성
        if (cards.size() != MIN_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.HANDS_CARDS_SIZE.getMessage());
        }
    }

    public int calculateTotalScore() {
        int totalScore = cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .orElse(0);

        if (totalScore <= BLACKJACK_MAX_SCORE - ACE_PROFIT_VALUE && hasAce(cards)) {
            return totalScore + ACE_PROFIT_VALUE;
        }

        return totalScore;
    }

    public boolean isBlackjack() {
        return (cards.size() == MIN_SIZE) && (calculateTotalScore() == BLACKJACK_MAX_SCORE);
    }

    public int getHandsSize() {
        return cards.size();
    }

    public void addCard(Card card) {
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
