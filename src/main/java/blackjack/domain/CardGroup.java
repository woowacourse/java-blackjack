package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGroup {
    private static final int BLACKJACK_NUMBER = 21;

    private final List<Card> cards = new ArrayList<>();

    public boolean isBust() {
        return getSum() > BLACKJACK_NUMBER;
    }

    public boolean isAddable() {
        return getSum() < BLACKJACK_NUMBER;
    }

    public int getSum() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getNumber)
                .sum();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addTwoCards(Card firstCard, Card secondCard) {
        cards.add(firstCard);
        cards.add(secondCard);
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void open() {
        for (Card card : cards) {
            card.open();
        }
    }
}
