package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private static final int ACE_VALUE_GAP = 10;
    private static final int LIMIT_SCORE = 21;

    private final List<Card> playerCards;
    private int totalScore;

    public HandCards() {
        this.playerCards = new ArrayList<>();
        this.totalScore = 0;
    }

    public void updateCardScore(Card card) {
        this.playerCards.add(card);
        totalScore = calculateTotalScore();
    }

    private int calculateTotalScore() {
        Integer totalScore = playerCards.stream()
                .map(card -> card.getNumber().getValue())
                .reduce(Integer::sum)
                .get();
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
}
