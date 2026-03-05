package domain.card;

import java.util.List;

public class CardDeckBuilder {

    private List<Card> cards = List.of(
    );

    public CardDeckBuilder cards(Card... cards) {
        this.cards = List.of(cards);
        return this;
    }

    public CardDeck build() {
        return CardDeck.from(TestCardGenerator.of(cards));
    }

}
