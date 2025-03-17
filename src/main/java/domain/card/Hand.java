package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BONUS_THRESHOLD = 11;
    private static final int ACE_BONUS = 10;
    private static final int BLACKJACK_COUNT = 2;

    private static final int BLACKJACK_NUMBER = 21;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int sumWithAce() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (sum <= BONUS_THRESHOLD && hasA()) {
            sum += ACE_BONUS;
        }

        return sum;
    }

    private boolean hasA() {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    public boolean isBust() {
        return sumWithAce() > BLACKJACK_NUMBER;
    }

    public boolean isBlackJackNumber() {
        return sumWithAce() == BLACKJACK_NUMBER;
    }

    public boolean isBlackJackCount() {
        return cards.size() == BLACKJACK_COUNT;
    }

    public Hand getFirstCard() {
        List<Card> firstOpenCards = new ArrayList<>(cards);
        firstOpenCards.removeLast();

        return new Hand(firstOpenCards);
    }

    public List<Card> getCards() {
        return cards;
    }

}
