package blackjack.domain;

import java.util.List;

public abstract class Player {
    private static final int BURST_CRITERIA = 21;

    private final String name;
    private final PlayingCards playingCards = new PlayingCards();

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

    public boolean isBurst() {
        return getResult() > BURST_CRITERIA;
    }
}
