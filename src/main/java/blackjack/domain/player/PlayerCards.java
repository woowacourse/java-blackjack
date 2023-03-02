package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private static final int INIT_TOTAL_SCORE = 0;

    private int totalScore;
    private final List<Card> playerCards;

    public PlayerCards() {
        this.totalScore = INIT_TOTAL_SCORE;
        this.playerCards = new ArrayList<>();
    }

    public void updateCardScore(Card card) {
        this.playerCards.add(card);
        calculateTotalScore(card);
    }

    private void calculateTotalScore(Card card) {
        this.totalScore += card.getNumber().getValue();
    }

    public List<Card> getPlayerCards() {
        return this.playerCards;
    }

    public int getTotalScore() {
        return this.totalScore;
    }
}
