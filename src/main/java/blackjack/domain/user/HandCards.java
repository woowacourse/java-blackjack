package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HandCards {
    public static final int BUST = 21;
    private static final int ACE_VALUE_GAP = 10;
    private static final int LIMIT_SCORE = 21;
    private static final int BLACKJACK = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> playerCards;
    private int totalScore;

    public HandCards() {
        this.playerCards = new ArrayList<>();
    }

    public void updateCardScore(Card card) {
        this.playerCards.add(card);
        totalScore = calculateTotalScore();
    }

    private int calculateTotalScore() {
        int totalScore = playerCards.stream()
                .map(card -> card.getNumber().getValue())
                .reduce(Integer::sum)
                .orElseThrow(() -> new NoSuchElementException("현재 저장된 카드가 없습니다."));
        return updateAceScore(totalScore);
    }

    private int updateAceScore(int totalScore) {
        int aceSize = getAceCardsSize();
        while (aceSize > 0 && totalScore > LIMIT_SCORE) {
            totalScore -= ACE_VALUE_GAP;
            aceSize--;
        }
        return totalScore;
    }

    private int getAceCardsSize() {
        return (int) playerCards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getPlayerCards() {
        return this.playerCards;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public boolean isBust() {
        return totalScore > LIMIT_SCORE;
    }

    public boolean isBlackjack() {
        return getTotalScore() == BLACKJACK && playerCards.size() == BLACKJACK_CARD_SIZE;
    }
}
