package domain.card;

import java.util.ArrayList;
import java.util.List;

public class PlayingCards {
    private static final int BLACKJACK_SCORE = 21;
    
    private final List<Card> cards;

    public PlayingCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        int totalScoreExceptAce = getTotalScoreExceptAce();

        if (containsAce()) {
            return getTotalScoreContainingAce(totalScoreExceptAce);
        }

        return totalScoreExceptAce;
    }

    private int getTotalScoreExceptAce() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private int getTotalScoreContainingAce(int totalScore) {
        if (totalScore <= 10) {
            return totalScore + 11;
        }

        return totalScore + 1;
    }

    public boolean isBurst() {
        return getTotalScore() > BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return cards;
    }
}
