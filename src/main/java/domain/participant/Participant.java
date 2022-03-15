package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import utils.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.compare;

public abstract class Participant implements Comparable<Participant> {

    protected static final int MAX_SCORE = 21;

    protected final Cards cards;
    protected final Name name;

    public Participant(String name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = new Name(name);
    }

    public abstract boolean canHit();

    public void hit(Deck deck) {
        if (!canHit()) {
            throw new IllegalStateException(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
        }
        cards.addCard(deck.handOut());
    }

    public void pickTwoCards(Deck deck) {
        this.cards.addCards(deck.handOutInitialTurn());
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    private boolean isBust() {
        return calculateScore() > MAX_SCORE;
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    @Override
    public int compareTo(Participant participant) {
        if (participant.isBust()) {
            return 1;
        }
        if (isBust()) {
            return -1;
        }

        return compare(calculateScore(), participant.calculateScore());
    }
}
