package balckjack.domain;

import balckjack.strategy.CardPicker;

abstract public class Participant {

    private final CardDeck cardDeck;

    public Participant() {
        cardDeck = new CardDeck();
    }

    public void hit(CardPicker cardPicker) {
        Card card = CardPool.draw(cardPicker);
        cardDeck.addCard(card);
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }
}
