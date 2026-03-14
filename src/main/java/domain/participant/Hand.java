package domain.participant;

import domain.card.Card;

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
            if (card.isAce()) {
                aceCount++;
            }
        }

        sum = adjustAceValue(sum, aceCount);

        return sum;
    }

    public void receive(Card card) {
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

    public List<Card> getCards() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("손 패가 존재하지 않습니다.");
        }

        return List.copyOf(cards);
    }
}
