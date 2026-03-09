package utils;

import domain.card.Card;
import domain.card.CardBundle;

import java.util.List;

public class CardBundleBuilder {

    private List<Card> cards = List.of();

    public CardBundleBuilder cards(Card... cards) {
        this.cards = List.of(cards);
        return this;
    }

    public CardBundleBuilder cards(List<Card> cards) {
        this.cards = cards;
        return this;
    }

    public CardBundle build() {
        return CardBundle.from(cards);
    }

}
