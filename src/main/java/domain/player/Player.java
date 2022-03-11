package domain.player;

import static java.util.Collections.unmodifiableList;

import domain.ScoreUtil;
import domain.card.PlayingCard;
import domain.card.PlayingCards;
import java.util.List;

public abstract class Player {
    private static final int BUST_CRITERIA = 21;
    private static final int BLACK_JACK = 21;

    private final String name;
    private final PlayingCards playingCards = new PlayingCards();

    protected Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addCard(PlayingCard playingCard) {
        playingCards.addCard(playingCard);
    }

    public int getPlayResult() {
        return ScoreUtil.getScore(playingCards.getPlayingCards());
    }

    public boolean isBust() {
        return getPlayResult() > BUST_CRITERIA;
    }

    public boolean isBlackJack() {
        return getPlayResult() == BLACK_JACK;
    }

    public List<PlayingCard> getPlayingCards() {
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
