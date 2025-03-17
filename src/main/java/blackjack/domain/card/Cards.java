package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(final Cards cards) {
        this.cards.addAll(cards.getCards());
    }

    public int sumCardScore() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public long countAceCard() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBlackJackSize() {
        return cards.size() == 2;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cards=" + cards +
                '}';
    }

    public List<Card> getCards() {
        return cards;
    }
}
