package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    public static final int BLACKJACK_CARD_SIZE = 2;
    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCards(final Cards cards) {
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
        return cards.size() == BLACKJACK_CARD_SIZE;
    }

    public Card getTopCard() {
        return cards.getFirst();
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
