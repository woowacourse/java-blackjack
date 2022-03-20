package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ExceptionMessages;

public abstract class Participant {

    protected static final int MAX_SCORE = 21;

    protected final Cards cards;
    protected final Name name;

    public Participant(String name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = new Name(name);
    }

    public final void hit(Card card) {
        if (!canDrawCard()) {
            throw new IllegalStateException(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
        }
        cards.addCard(card);
    }

    public abstract boolean canDrawCard();

    public final boolean isBurst() {
        return score() > MAX_SCORE;
    }

    public final boolean isBlackJack() {
        return score() == MAX_SCORE && cards.size() == 2;
    }

    public final boolean isNotBlackJack() {
        return score() != MAX_SCORE;
    }

    public final int score() {
        return cards.calculateScore();
    }

    public final void hitInitialTurn(Deck deck) {
        cards.addCards(deck.handOutInitialTurn());
    }

    public final String getName() {
        return name.getValue();
    }

    public final List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
