package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    protected final Cards hand;
    private final Name name;

    private final static int BLACK_JACK_SCORE = 21;
    private final static int TWO_CARDS = 2;

    public Player(final Cards cards, final Name name) {
        this.hand = new Cards(cards.getCardsAsList());
        this.name = new Name(name.getName());
    }

    public abstract List<Card> getInitCardsAsList();

    public List<Card> getCardsAsList() {
        return hand.getCardsAsList();
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
