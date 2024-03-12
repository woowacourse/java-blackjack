package domain.gamer;

import domain.card.Card;

public class Player extends Gamer {

    private static final int MAX_BLACK_JACK_SCORE = 21;
    private static final int BUST = 0;
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isDrawable() {
        return cards.getMinGameScore() <= MAX_BLACK_JACK_SCORE && cards.countMaxScore() != BUST;
    }

    public Name getName() {
        return name;
    }
}
