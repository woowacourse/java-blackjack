package blackjack.domain;

import java.util.List;

public abstract class Player {

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

    public abstract boolean isNotFinished();

    public int getResult() {
        return playingCards.getResult();
    }

    public List<PlayingCard> getCards() {
        return playingCards.getPlayingCards();
    }

    @Override
    public String toString() {
        return "Player{" +
            "name='" + name + '\'' +
            ", playingCards=" + playingCards +
            '}';
    }
}
