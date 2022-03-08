package blackjack.domain;

import java.util.ArrayList;

public abstract class Player {
    private final String name;
    private PlayingCards playingCards = new PlayingCards();

    protected Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addCard(PlayingCard playingCard) {
        playingCards.addCard(playingCard);
    }

    public int getResult() {
        return playingCards.getResult();
    }
}
