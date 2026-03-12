package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    
    public static final int ACE_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private final List<Card> cards;
    
    public Hand() {
        cards = new ArrayList<>();
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void addCards(List<Card> drewCards) {
        cards.addAll(drewCards);
    }
    
    public List<Card> getCards() {
        return List.copyOf(cards);
    }
    
    public boolean isBlackjack() {
        return getTotalScore() == BLACKJACK_SCORE && cards.size() == 2;
    }
    
    public boolean isBust() {
        int totalScore = getTotalScore();
        return totalScore > BLACKJACK_SCORE;
    }
    
    public boolean isUnderBlackjackScore() {
        int totalScore = getTotalScore();
        return totalScore < BLACKJACK_SCORE;
    }
    
    public int getSize() {
        return cards.size();
    }
    
    public int getTotalScore() {
        int scoreSum = calculateScoreSum();
        if (canAddAceScore(scoreSum)) {
            return scoreSum + ACE_SCORE;
        }
        return scoreSum;
    }
    
    private boolean canAddAceScore(int scoreSum) {
        return hasAce() && scoreSum + ACE_SCORE <= BLACKJACK_SCORE;
    }
    
    private int calculateScoreSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
    
    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
