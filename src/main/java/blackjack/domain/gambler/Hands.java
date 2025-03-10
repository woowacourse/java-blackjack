package blackjack.domain.gambler;

import blackjack.domain.Rule;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {
    private final List<Card> cards;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = calculateSum();
        long aceCount = calculateAceCount();
        return Rule.adjustSumByAce(sum, (int) aceCount);
    }

    private int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getTypeValue)
                .sum();
    }

    private long calculateAceCount() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isScoreBelow(final int criteria) {
        return calculateScore() <= criteria;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
