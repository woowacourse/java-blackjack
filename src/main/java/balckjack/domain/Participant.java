package balckjack.domain;

import balckjack.strategy.CardPicker;

abstract public class Participant {

    private static final int INIT_CARD_COUNT = 2;

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

    public void initHit(CardPicker cardPicker) {
        for (int count = 0; count < INIT_CARD_COUNT; count++) {
            hit(cardPicker);
        }
    }
}
