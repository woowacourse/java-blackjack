package domain.participant;

import domain.game.Bet;
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
    private final Bet bet;

    protected Participant(Name name, int bet) {
        this.name = name;
        this.cards = Cards.create(Collections.emptyList());
        this.bet = Bet.of(bet);
    }

    protected Participant(Name name, Cards cards, int bet) {
        this.name = name;
        this.cards = cards;
        this.bet = Bet.of(bet);
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
