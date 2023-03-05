package domain.cardtable;

import domain.area.CardArea;
import domain.deck.CardDeck;

public class CardTable {

    private final CardDeck cardDeck;

    private CardTable(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public static CardTable readyToPlayBlackjack(final CardDeck cardDeck) {
        return new CardTable(cardDeck);
    }

    public CardArea createCardArea() {
        return new CardArea(cardDeck.draw(), cardDeck.draw());
    }
}
