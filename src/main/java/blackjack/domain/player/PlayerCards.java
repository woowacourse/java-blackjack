package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private static final int ACE_VALUE_GAP = 10;
    private static final int LIMIT_SCORE = 21;

    private final List<Card> playerCards;
    private int totalScore;

    public PlayerCards() {
        this.playerCards = new ArrayList<>();
        this.totalScore = 0;
    }

    public void updateCardScore(Card card) {
        this.playerCards.add(card);
        totalScore = calculateTotalScore();
    }

    private int calculateTotalScore() {
        int totalScore = 0;
        for (Card card : playerCards) {
            totalScore += card.getNumber().getValue();
        }
        return updateAceScore(totalScore);
    }

    private int updateAceScore(int totalScore) {
        int aceSize = getAceCardsSize();
        while (aceSize > 0 && totalScore > LIMIT_SCORE) {
            totalScore -= ACE_VALUE_GAP;
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
