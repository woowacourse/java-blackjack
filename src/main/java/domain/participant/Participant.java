package domain.participant;

import domain.deck.DeckStrategy;
import domain.game.Bet;
import domain.game.GamePoint;
import domain.card.Card;
import domain.card.Cards;

import java.util.Collections;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;
    protected static final String DEALER_NAME = "딜러";

    private final Name name;
    protected Cards cards;

    protected Participant(final Name name) {
        this.name = name;
        this.cards = Cards.create(Collections.emptyList());
    }

    protected Participant(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
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

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public Name getName() {
        return name;
    }
}
