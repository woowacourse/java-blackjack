package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK = 21;
    private static final int BLACKJACK_HAND_SIZE = 2;
    private static final int ACE_ADJUSTMENT_VALUE = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public int calculateScore() {
        int sum = 0;
        int aceCount = 0;

        for (Card card : cards) {
            sum += card.getRank().getValue();
            if (card.isAce()) aceCount++;
        }

        sum = adjustAceValue(sum, aceCount);

        return sum;
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_HAND_SIZE && calculateScore() == BLACKJACK;
    }

    private int adjustAceValue(int sum, int aceCount) {
        while (sum > BLACKJACK && aceCount > 0) {
            sum -= ACE_ADJUSTMENT_VALUE;
            aceCount--;
        }

        return sum;
    }

    public Card getFirstCard() {
        if (!cards.isEmpty()) {
            return cards.getFirst();
        }

        throw new IllegalArgumentException("손 패가 존재하지 않습니다.");
    }

    public List<Card> getCards() {
        if (!cards.isEmpty()) {
            return List.copyOf(cards);
        }

        throw new IllegalArgumentException("손 패가 존재하지 않습니다.");
    }
}
