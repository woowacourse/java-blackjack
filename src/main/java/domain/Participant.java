package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final String name;
    private final List<Card> cards;

    public Participant(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public abstract List<Card> getShownCard();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getTotalValue() {
        final long constTotalValue = cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(Card::getValue)
            .sum();

        final long aceCount = cards.stream()
            .filter(Card::isAce)
            .count();

        long totalValue = constTotalValue;
        for (int i = 0; i <= aceCount; ++i) {
            totalValue = constTotalValue + (i * 1) + ((aceCount - i) * 11);
            if (totalValue <= 21) {
                return (int) totalValue;
            }
        }
        return (int) totalValue;
    }

    public boolean isOverThan(int standardValue) {
        return getTotalValue() > standardValue;
    }

    @Override
    public String toString() {
        return "Participant{" +
            "name='" + name + '\'' +
            ", cards=" + cards +
            '}';
    }
}
