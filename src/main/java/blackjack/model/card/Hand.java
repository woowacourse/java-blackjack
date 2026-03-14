package blackjack.model.card;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public final class Hand {

    private static final int BUST_LOWER_BOUND = 22;
    private static final int ACE_ADJUST_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public Hand() {
        this.cards = List.of();
    }

    public Hand(Collection<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Hand addCard(Card newCard) {
        List<Card> newCards = Stream.concat(cards.stream(), Stream.of(newCard))
                .toList();

        return new Hand(newCards);
    }

    public boolean isBust() {
        return isBust(calculateScore());
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_SCORE
                && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public int calculateScore() {
        int scoreBeforeAdjust = getScoreBeforeAdjust();

        return adjust(scoreBeforeAdjust);
    }

    private boolean isSoftHand(int scoreAfterAdjust) {
        boolean containAce = cards.stream()
                .anyMatch(Card::isAce);

        return isNotBust(scoreAfterAdjust) && containAce;
    }

    private boolean isBust(int score) {
        return score >= BUST_LOWER_BOUND;
    }

    private boolean isNotBust(int score) {
        return !isBust(score);
    }

    private int getScoreBeforeAdjust() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int adjust(int scoreBeforeAdjust) {
        int scoreAfterAdjust = scoreBeforeAdjust + ACE_ADJUST_SCORE;

        if (isSoftHand(scoreAfterAdjust)) {
            return scoreAfterAdjust;
        }

        return scoreBeforeAdjust;
    }
}
