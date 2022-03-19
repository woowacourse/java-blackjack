package blackjack_statepattern;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return 11 == sum() && hasAce();
    }
}
