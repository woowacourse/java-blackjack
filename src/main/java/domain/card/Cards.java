package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private final List<Card> cards;

    private Cards() {
        this.cards = new ArrayList<>();
    }

    public static Cards create() {
        return new Cards();
    }

    public void takeCard(final Card card) {
        cards.add(card);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
