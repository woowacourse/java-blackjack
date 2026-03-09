package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<String> getNames() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public String getFirstName() {
        return cards.getFirst().getName();
    }

    public int calculateValue() {
        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        return applyBestAceValue(sum);
    }

    private int applyBestAceValue(int sum) {
        if (hasAce() && (sum + 10) <= 21) {
            return sum + 10;
        }

        return sum;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }
}
