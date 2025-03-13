package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(final Cards cards) {
        this.cards.addAll(cards.cards);
    }

    public int sumCardScore() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public int countAceCard() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBlackJackSize() {
        return cards.size() == 2;
    }
}
