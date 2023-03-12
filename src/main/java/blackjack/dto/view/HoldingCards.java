package blackjack.dto.view;

import blackjack.domain.card.Card;

import java.util.List;

public class HoldingCards {

    private final String name;
    private final List<Card> cards;

    public HoldingCards(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = List.copyOf(cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
