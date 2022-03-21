package blackjack.domain.machine;

import blackjack.domain.card.Card;
import java.util.Objects;
import java.util.Set;

public class Score {

    private static final int ACE_ADDITIONAL_POINT = 10;
    private static final int BUST_LIMIT = 21;
    private static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_SIZE = 2;

    private final int value;
    private final boolean blackjack;

    public Score(Set<Card> deck) {
        this.value = sumPoints(deck);
        blackjack = checkBlackjack(deck);
    }

    public int value() {
        return value;
    }

    public boolean isBlackjack() {
        return blackjack;
    }

    public boolean isBlackjackWin(Score score) {
        return blackjack && score.nonBlackjack();
    }

    public boolean isDraw(Score score) {
        return Objects.equals(this.value, score.value);
    }

    public boolean isLose(Score score) {
        if (isBust()) {
            return true;
        }
        return value < score.value && score.nonBust();
    }

    public boolean isOverLimit(int limit) {
        return value > limit;
    }

    public boolean isWin(Score score) {
        return value > score.value && value < BUST_LIMIT;
    }

    private int sumPoints(Set<Card> deck) {
        int total = deck.stream()
                .mapToInt(Card::point)
                .sum();
        if (canAddAcePoint(deck, total)) {
            total += ACE_ADDITIONAL_POINT;
        }
        return total;
    }

    private boolean canAddAcePoint(Set<Card> deck, int total) {
        return hasAce(deck) && total + ACE_ADDITIONAL_POINT <= BUST_LIMIT;
    }

    private boolean hasAce(Set<Card> deck) {
        return deck.stream()
                .anyMatch(Card::isAce);
    }

    private boolean checkBlackjack(Set<Card> deck) {
        return !isNotTwoCards(deck) && !nonBlackjack();
    }

    private boolean nonBlackjack() {
        return value != BLACKJACK_NUMBER;
    }

    private boolean isNotTwoCards(Set<Card> deck) {
        return deck.size() != BLACKJACK_SIZE;
    }

    private boolean isBust() {
        return value > BUST_LIMIT;
    }

    private boolean nonBust() {
        return value <= BUST_LIMIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Score score = (Score) o;

        if (value != score.value) {
            return false;
        }
        return blackjack == score.blackjack;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (blackjack ? 1 : 0);
        return result;
    }
}
