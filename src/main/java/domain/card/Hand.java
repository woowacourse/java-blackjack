package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int INIT_GIVE_CARD_COUNT = 2;
    private static final Score BLACKJACK = new Score(21);
    
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score getTotalScore() {
        Score totalScore = score();
        if (containsAce()) {
            return totalScore.plusTenIfNotBust();
        }
        
        return totalScore;
    }
    
    private Score score() {
        return new Score(calculateTotalScore());
    }
    
    private int calculateTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
    
    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return getTotalScore().isBust();
    }
    
    public boolean isNotEnoughInitCardsCount() {
        return cards.size() < INIT_GIVE_CARD_COUNT;
    }
    
    public boolean isBlackJack() {
        return getTotalScore().isSameTo(BLACKJACK);
    }
    
    public List<Card> getCards() {
        return cards;
    }
}
