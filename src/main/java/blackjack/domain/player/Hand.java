package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int ACE_VALUE_GAP = 10;
    private static final int LIMIT_SCORE = 21;

    private final List<Card> playerCards;
    private TotalScore totalScore;

    public Hand() {
        this.playerCards = new ArrayList<>();
        this.totalScore = new TotalScore(0);
    }

    public void addCard(Card card) {
        this.playerCards.add(card);
    }

    public void updateTotalScore() {
        int newTotalScore = 0;
        for (Card card : playerCards) {
            newTotalScore += card.getCardNumber().getValue();
        }
        newTotalScore = updateAceScore(newTotalScore);
        this.totalScore = new TotalScore(newTotalScore);
    }

    private int updateAceScore(int totalScore) {
        int aceSize = calculateCardSize();
        while (aceSize > 0 && totalScore > LIMIT_SCORE) {
            totalScore -= ACE_VALUE_GAP;
            aceSize--;
        }
        return totalScore;
    }

    public Status status(){
        return Status.of(playerCards.size(),totalScore.getTotalScore());
    }

    private int calculateCardSize() {
        return (int) playerCards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getPlayerCards() {
        return this.playerCards;
    }

    public int getTotalScore() {
        return totalScore.getTotalScore();
    }
}
