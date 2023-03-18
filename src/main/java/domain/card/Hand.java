package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final Score BLACKJACK = new Score(21);
    
    private final List<Card> cards;
    
    public Hand() {
        this.cards = new ArrayList<>();
    }
    
    public void addAllCard(Card firstCard, Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public Score getTotalScore() {
        Score totalScore = calculateScore();
        if (containsAce()) {
            return totalScore.plusTenIfLessThenOrEqualTo(BLACKJACK);
        }
        
        return totalScore;
    }
    
    private Score calculateScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score::addScore)
                .orElseThrow(() -> new IllegalStateException("스코어가 존재하지 않습니다."));
    }
    
    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }
    
    public boolean isBust() {
        return getTotalScore().isOverThen(BLACKJACK);
    }
    
    public boolean isBlackJack() {
        return getTotalScore().equals(BLACKJACK);
    }
    
    public List<Card> getCards() {
        return cards;
    }
}
