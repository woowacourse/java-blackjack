package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {
    private final List<Card> cards;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public void addNewCard(Card card) {
        cards.add(card);
    }

    public int calculateSum() {
        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        long aceCount = cards.stream()
                .filter(Card::isAce)
                .count();
        return considerAce(sum, (int) aceCount);
    }

    private int considerAce(int sum, int aceCount) {
        if (sum <= 21) {
            return sum;
        }
        while (aceCount > 0 && sum > 21) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

    public boolean isSumBelow(final int criteria) {
        return calculateSum() <= criteria;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
