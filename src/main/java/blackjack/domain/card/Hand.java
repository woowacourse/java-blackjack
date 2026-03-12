package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    
    public static final int ACE_SCORE = 10;
    private static final int BUST_SCORE = 21;
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
        return getTotalScore() == BUST_SCORE;
    }
    
    public boolean isBust() {
        int totalScore = getTotalScore();
        return totalScore > BUST_SCORE;
    }
    
    public int getTotalScore() {
        int scoreSum = calculateScoreSum();
        if (hasAce() && (scoreSum + ACE_SCORE <= BUST_SCORE)) {
            return scoreSum + ACE_SCORE;
        }
        return scoreSum;
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
