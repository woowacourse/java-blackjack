package domain;

import java.util.List;
import java.util.Objects;

public class Dealer {

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public boolean checkExceedSixteen() {
        return cards.checkExceedSixteen();
    }

    public Dealer drawCard(List<Card> providedCards) {
        return new Dealer(cards.addCards(providedCards));
    }

    public boolean checkExceedTwentyOne() {
        return cards.checkExceedTwentyOne();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(cards, dealer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
