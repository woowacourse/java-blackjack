package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> deck) {
        this.cards = new ArrayList<>(deck);
    }

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public String showCards() {
        return "3스페이드";
    }
}
