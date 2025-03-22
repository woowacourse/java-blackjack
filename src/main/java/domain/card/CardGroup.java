package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGroup {

    private static final int ACE_DISTANCE_SCORE = 10;
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACKJACK_CARD_QUANTITY = 2;

    private final List<Card> cards;

    public CardGroup(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static CardGroup empty() {
        return new CardGroup(Collections.emptyList());
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }


    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_QUANTITY && calculateScore() == BUST_THRESHOLD;
    }

    public int calculateScore() {
        return calculateScoreWithAce(calculateScoreWithOutAce(), countAce());
    }

    private int calculateScoreWithOutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(card -> card.score().getValue())
                .sum();
    }

    private int calculateScoreWithAce(int sum, int aceCount) {
        final int result = sum + aceCount;
        return result + addScoreWithAce(0, BUST_THRESHOLD - result, aceCount);
    }

    private int addScoreWithAce(int sum, int limit, int aceCount) {
        if (aceCount <= 0 || sum + ACE_DISTANCE_SCORE > limit) {
            return sum;
        }
        return addScoreWithAce(sum + ACE_DISTANCE_SCORE, limit, aceCount - 1);
    }

    private int countAce() {
        return Math.toIntExact(cards.stream()
                .filter(Card::isAce)
                .count());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
