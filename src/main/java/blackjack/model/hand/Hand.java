package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public abstract class Hand {

    private static final int BUST_LOWER_BOUND = 22;
    private static final int ACE_ADJUST_SCORE = 10;
    private static final int BLACKJACK = 21;

    protected final Collection<Card> cards;

    protected Hand() {
        this.cards = List.of();
    }

    protected Hand(Collection<Card> existCards, Card newCard) {
        this.cards = Stream.concat(existCards.stream(), Stream.of(newCard))
                .toList();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public abstract Hand hit(Card newCard);

    public abstract boolean canHit();

    public double getEarningRate() {
        return 1;
    }

    public boolean isBust() {
        return isBust(calculateScore());
    }

    public int calculateScore() {
        int scoreBeforeAdjust = getScoreBeforeAdjust();

        return adjust(scoreBeforeAdjust);
    }

    protected boolean isBustWith(Card newCard) {
        return isBust(calculateScoreWith(newCard));
    }

    protected int calculateScoreWith(Card newCard) {
        int scoreBeforeAdjust = getScoreBeforeAdjustWith(newCard);

        return adjustWith(scoreBeforeAdjust, newCard);
    }

    protected boolean isBlackjack() {
        return calculateScore() == BLACKJACK;
    }

    protected boolean isBlackjackWith(Card newCard) {
        return calculateScoreWith(newCard) == BLACKJACK;
    }

    private boolean isSoftHand(int scoreAfterAdjust) {
        boolean containAce = cards.stream()
                .anyMatch(Card::isAce);

        return isNotBust(scoreAfterAdjust) && containAce;
    }

    private boolean isSoftHandWith(int scoreAfterAdjust, Card newCard) {
        boolean containAce = Stream.concat(cards.stream(), Stream.of(newCard))
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

    private int getScoreBeforeAdjustWith(Card newCard) {
        return Stream.concat(cards.stream(), Stream.of(newCard))
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

    private int adjustWith(int scoreBeforeAdjust, Card newCard) {
        int scoreAfterAdjust = scoreBeforeAdjust + ACE_ADJUST_SCORE;

        if (isSoftHandWith(scoreAfterAdjust, newCard)) {
            return scoreAfterAdjust;
        }

        return scoreBeforeAdjust;
    }
}
