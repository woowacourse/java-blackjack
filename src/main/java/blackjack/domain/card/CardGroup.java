package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.property.CardNumber;

public class CardGroup {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_SPECIAL_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return getSum() > BLACKJACK_NUMBER;
    }

    public boolean isBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }

    public boolean isAddable() {
        return getSum() < BLACKJACK_NUMBER;
    }

    public int getScore() {
        int sum = getSum();
        boolean containsAce = containsAce();
        if (containsAce && sum + ACE_SPECIAL_SCORE > BLACKJACK_NUMBER) {
            return sum;
        }
        if (containsAce) {
            return sum + ACE_SPECIAL_SCORE;
        }
        return sum;
    }

    private int getSum() {
        return cards.stream()
            .map(Card::getCardNumber)
            .mapToInt(CardNumber::getNumber)
            .sum();
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isA);
    }

    public void open() {
        for (Card card : cards) {
            card.open();
        }
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && getScore() == BLACKJACK_NUMBER;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
