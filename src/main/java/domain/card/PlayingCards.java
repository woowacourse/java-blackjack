package domain.card;

import java.util.ArrayList;
import java.util.List;

public class PlayingCards {
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_BOTH_SCORE_DIFFERENCE = 10;
    
    private final List<Card> cards;

    public PlayingCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        int totalScore = calculateTotalScore();

        if (isBurst(totalScore) && containsAce()) {
            return totalScore - ACE_BOTH_SCORE_DIFFERENCE;
        }

        return totalScore;
    }
    
    public boolean isBurst(int totalScore) {
        return totalScore > BLACKJACK_SCORE;
    }
    
    private int calculateTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}
