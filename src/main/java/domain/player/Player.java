package domain.player;

import domain.deck.Card;

import java.util.List;

public class Player {
    private static final int BLACK_JACK_NUMBER = 21;

    private final Name name;
    private final Hand hand;

    public Player(final String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void drawCard(final Card card) {
        hand.addCard(card);
    }

    public boolean isWin(final int dealerScore) {
        if (dealerScore > BLACK_JACK_NUMBER && hand.score() <= BLACK_JACK_NUMBER) {
            return true;
        }
        return hand.score() <= BLACK_JACK_NUMBER && hand.score() > dealerScore;
    }

    public boolean isDraw(final int dealerScore) {
        if (dealerScore > BLACK_JACK_NUMBER && hand.score() > BLACK_JACK_NUMBER) {
            return true;
        }
        return dealerScore == hand.score();
    }

    public boolean isOver21() {
        if (getScore() > BLACK_JACK_NUMBER) {
            return true;
        }
        return false;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return hand.score();
    }
}
