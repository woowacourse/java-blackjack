package domain.playingcard;

import java.util.*;
import java.util.stream.IntStream;

public class Deck {
    private static final int INIT_DRAWN_COUNT = 2;

    private final Queue<PlayingCard> playingCards;

    public Deck(final Queue<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck init(final List<PlayingCard> playingCards) {
        Collections.shuffle(playingCards);

        return new Deck(new ArrayDeque<>(playingCards));
    }

    public List<PlayingCard> initDrawn() {
        return IntStream.range(0, INIT_DRAWN_COUNT)
                .mapToObj(i -> drawn())
                .toList();
    }

    public PlayingCard drawn() {
        return playingCards.poll();
    }
}
