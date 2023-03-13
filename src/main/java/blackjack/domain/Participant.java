package blackjack.domain;

import blackjack.strategy.CardPicker;

public class Participant {

    private static final int INIT_CARD_COUNT = 2;

    private final CardDeck cardDeck;
    private final Name name;

    public Participant(String name) {
        this.name = new Name(name);
        cardDeck = new CardDeck();
    }

    public void hit(CardPicker cardPicker) {
        Card card = CardPool.draw(cardPicker);
        cardDeck.addCard(card);
    }

    public Name getName() {
        return name;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public void distributeCards(CardPicker cardPicker) {
        for (int count = 0; count < INIT_CARD_COUNT; count++) {
            hit(cardPicker);
        }
    }

    public boolean isBlackJack() {
        return cardDeck.isBlackJack();
    }

    public boolean isBust() {
        return cardDeck.calculateScore().isBust();
    }
}
