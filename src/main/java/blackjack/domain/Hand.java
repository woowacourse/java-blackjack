package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    
    protected static final int BUSTED_SCORE = 21;
    
    private final List<Card> cards;
    
    public Hand() {
        cards = new ArrayList<>();
    }
    
    public String addCard(List<Card> receivedCards) {
        cards.addAll(receivedCards);
        return getSnapshot();
    }
    
    public String getSnapshot() {
        return cards
                .stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }
    
    public String getFirstSnapshot() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("남은 카드가 없습니다.");
        }
        return cards.getFirst().getDisplayName();
    }
    
    public boolean isBlackjack() {
        return getTotalScore() == BUSTED_SCORE;
    }
    
    public boolean isBusted() {
        int totalScore = getTotalScore();
        return totalScore > BUSTED_SCORE;
    }
    
    public int getTotalScore() {
        int scoreSum = calculateScoreSum();
        if (hasAce() && (scoreSum + 10 <= BUSTED_SCORE)) {
            System.out.println("has?: " + hasAce());
            System.out.println("scoreSum?: " + scoreSum);
            return scoreSum + 10;
        }
        return scoreSum;
    }
    
    private int calculateScoreSum() {
        return cards
                .stream()
                .mapToInt(Card::getScore)
                .sum();
    }
    
    private boolean hasAce() {
        return cards
                .stream()
                .anyMatch(Card::isAce);
    }
}
