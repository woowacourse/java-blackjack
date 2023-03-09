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
    private final Bet bet;
    protected Cards cards;

    protected Participant(final Name name, final int bet) {
        this.name = name;
        this.cards = Cards.create(Collections.emptyList());
        this.bet = Bet.of(bet);
    }

    protected Participant(final Name name, final Cards cards, final int bet) {
        this.name = name;
        this.cards = cards;
        this.bet = Bet.of(bet);
    }

    public void takeCard(final Card card) {
        this.cards = cards.add(card);
    }

    public void takeCard(final DeckStrategy deck, final int count) {
        for (int i = 0; i < count; i++) {
            this.cards = cards.add(deck.drawCard());
        }
    }

    public Cards getCards() {
        return cards;
    }

    public Bet getBet() {
        return bet;
    }

    public void applyBust() {
        bet.applyBust();
    }

    public void applyBlackJack() {
        bet.applyBlackJack();
    }

    public GamePoint calculatePoint() {
        return cards.getGamePoint();
    }

    public boolean isBusted() {
        return cards.isBusted();
    }

    public boolean isBlackJack() {
        return cards.getGamePoint().isBlackJack();
    }

    public Name getName() {
        return name;
    }
}
