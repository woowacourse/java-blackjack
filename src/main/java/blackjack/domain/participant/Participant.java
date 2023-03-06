package blackjack.domain.participant;

import blackjack.domain.CardPool;
import blackjack.domain.card.Card;
import blackjack.strategy.CardPicker;

abstract public class Participant {

    private static final int INIT_COUNT = 2;

    private final CardDeck cardDeck;
    private final Name name;

    public Participant(String name) {
        this.name = new Name(name);
        cardDeck = new CardDeck();
    }

    public void initHit(CardPool cardPool, CardPicker cardPicker) {
        for (int i = 0; i < INIT_COUNT; i++) {
            hit(cardPool.draw(cardPicker));
        }
    }

    public void hit(Card card) {
        cardDeck.addCard(card);
    }

    public int calculateScore() {
        return cardDeck.calculateScore(cardDeck);
    }

    public Name getName() {
        return name;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }
}
