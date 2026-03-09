package domain.card;

import java.util.List;

public class CardDeckBuilder {

    private List<Card> cards = List.of(
    );

    public CardDeckBuilder cards(Card... cards) {
        this.cards = List.of(cards);
        return this;
    }

    public CardDeckBuilder cards(List<Card> cards) {
        this.cards = cards;
        return this;
    }

    public CardDeck build() {
        return CardDeck.from(TestCardDeckGenerator.of(cards));
    }

}
