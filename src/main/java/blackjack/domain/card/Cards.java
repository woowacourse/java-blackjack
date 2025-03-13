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

    public List<Card> getCards() {
        return cards;
    }
}
