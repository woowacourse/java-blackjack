package domain.playingcard;

import java.util.*;

public class Deck {
    private final Queue<PlayingCard> playingCards;

    public Deck(final Queue<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck init(final List<PlayingCard> playingCards) {
        Collections.shuffle(playingCards);
        return new Deck(new ArrayDeque<>(playingCards));
    }

    public PlayingCard drawn() {
        return playingCards.poll();
    }
}
