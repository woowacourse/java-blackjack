package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BUST_THRESHOLD = 21;
    private static final int ACE_BONUS = 10;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    private Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
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

    public Hand addCard(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && calculateScore() == BUST_THRESHOLD;
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
