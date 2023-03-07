package domain.participant;

import domain.game.Deck;
import domain.game.GamePoint;
import domain.card.Card;
import domain.card.Cards;

import java.util.Collections;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;
    protected static final String DEALER_NAME = "딜러";

    private final Name name;
    protected Cards cards;

    protected Participant(Name name) {
        this.name = name;
        this.cards = Cards.create(Collections.emptyList());
    }

    protected Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void takeCard(final Card card) {
        this.cards = cards.add(card);
    }

    public void takeCard(final Deck deck, final int count) {
        for (int i = 0; i < count; i++) {
            this.cards = cards.add(deck.drawCard());
        }
    }

    public Cards getCards() {
        return cards;
    }

    public GamePoint calculatePoint() {
        return cards.getGamePoint();
    }

    public boolean isBusted() {
        return cards.isBusted();
    }

    public Name getName() {
        return name;
    }
}
