package domain.player;

import static java.util.Collections.unmodifiableList;

import domain.card.PlayingCard;
import domain.card.PlayingCards;
import java.util.List;

public abstract class Player {
    private static final int BUST_CRITERIA = 21;

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

    public boolean isBust() {
        return getResult() > BUST_CRITERIA;
    }

    public List<PlayingCard> getCards() {
        return unmodifiableList(playingCards.getPlayingCards());
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", playingCards=" + playingCards +
                '}';
    }
}
