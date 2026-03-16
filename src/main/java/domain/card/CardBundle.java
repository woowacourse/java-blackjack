package domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardBundle {
    private final List<Card> cards;
    private static final int BUST_THRESHOLD = 21;
    private static final int ACE_BONUS = 10;

    public CardBundle() {
        this.cards = new ArrayList<>();
    }

    public int calculateScore() {
        int basicScore = calculateBasicScore();
        boolean hasAce = hasAce();

        return applyAceBonus(basicScore, hasAce);
    }

    private int calculateBasicScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int applyAceBonus(int score, boolean hasAce) {
        if (isSoftHand(score, hasAce)) {
            return score + ACE_BONUS;
        }
        return score;
    }

    private boolean isSoftHand(int score, boolean hasAce) {
        return hasAce && score + ACE_BONUS <= BUST_THRESHOLD;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculateScore() == BUST_THRESHOLD;
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
