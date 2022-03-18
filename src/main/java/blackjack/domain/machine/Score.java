package blackjack.domain.machine;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.participant.Guest;
import java.util.Set;

public class Score {

    private static final int BONUS_ACE_POINT = 10;

    private final Integer point;
    private boolean blackjack;

    public Score(Set<Card> cards) {
        this.point = sumPoints(cards);
        checkBlackjack(cards);

    }

    public int getScore() {
        return point;
    }


    public boolean isBlackjack() {
        return blackjack;
    }

    private int sumPoints(Set<Card> cards) {
        int sumWithoutAce = cards.stream()
                .filter(this::excludeAce)
                .mapToInt(this::getCardPoint)
                .sum();
        int aceCount = countAces(cards);
        if (aceCount == 0) {
            return sumWithoutAce;
        }
        return calculateAcePoint(sumWithoutAce, aceCount);
    }

    private void checkBlackjack(Set<Card> cards) {
        if (isNotTwoCards(cards) || isNotTwentyOnePoint()) {
            return;
        }
        blackjack = true;
    }

    private boolean isNotTwentyOnePoint() {
        return point != 21;
    }

    private boolean isNotTwoCards(Set<Card> cards) {
        return cards.size() != 2;
    }

    private int countAces(Set<Card> cards) {
        return (int) cards.stream()
                .filter(card -> !excludeAce(card))
                .count();
    }

    private int calculateAcePoint(int sumWithoutAce, int aceCount) {
        if (sumWithoutAce + aceCount + BONUS_ACE_POINT <= Guest.LIMIT_POINT) {
            return sumWithoutAce + aceCount + BONUS_ACE_POINT;
        }
        return sumWithoutAce + aceCount;
    }

    private boolean excludeAce(Card card) {
        return !card.getRank().equals(Rank.ACE);
    }

    private int getCardPoint(Card card) {
        return card.getRank().getPoint();
    }

    public boolean isBust() {
        return point > Guest.LIMIT_POINT;
    }
}
