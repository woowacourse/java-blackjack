package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cards implements Iterable<Card> {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int getTotalScore() {
        return cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getValue)
                .sum();
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean hasTwoCards() {
        return cards.size() == 2;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
