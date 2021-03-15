package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private final static int BLACK_JACK_SCORE = 21;
    private final static int TWO_CARDS = 2;

    protected final Cards hand;
    protected Profit profit;
    private final Name name;

    public Participant(final Cards cards, final String name) {
        this.hand = new Cards(cards.getCardsAsList());
        this.name = new Name(name);
    }

    public abstract List<Card> getInitCardsAsList();

    public abstract double getProfit();

    public List<Card> getCardsAsList() {
        return new ArrayList<>(hand.getCardsAsList());
    }

    public int getScore() {
        return this.hand.getScore();
    }

    public String getNameAsString() {
        return name.getName();
    }

    public void receiveMoreCard(final Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.getScore() == BLACK_JACK_SCORE && hand.size() == TWO_CARDS;
    }
}
