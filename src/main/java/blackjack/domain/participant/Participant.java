package blackjack.domain.participant;

import blackjack.domain.game.Deck;
import blackjack.domain.card.Card;

abstract public class Participant {

    private static final int INIT_COUNT = 2;

    private final Cards cards;
    private final Name name;

    public Participant(String name) {
        this.name = new Name(name);
        cards = new Cards();
    }

    public void initHit(Deck deck) {
        for (int i = 0; i < INIT_COUNT; i++) {
            hit(deck.draw());
        }
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBlackJack() {
        return cards.isBlackjack();
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
