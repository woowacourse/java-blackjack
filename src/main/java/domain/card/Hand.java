package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ACE_VALUE_GAP = 10;
    private static final int BUST_THRESHOLD = 21;
    private static final int INITIAL_CARD_COUNT = 2;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return getResultScore() > BUST_THRESHOLD;
    }

    public boolean isBlackJack() {
        return this.getResultScore() == BUST_THRESHOLD && cards.size() == INITIAL_CARD_COUNT;
    }

    public int getResultScore() {
        int total = getTotalScore();
        if (total <= BUST_THRESHOLD - ACE_VALUE_GAP && containsAce()) {
            return total + ACE_VALUE_GAP;
        }
        return total;
    }

    private int getTotalScore() {
        return cards.stream()
                .map(Card::getCardNumber)
                .map(CardNumber::getScore)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
