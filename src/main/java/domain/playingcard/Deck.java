package domain.playingcard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private final Queue<PlayingCard> playingCards;

    Deck(final Queue<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck init(final List<PlayingCard> playingCards) {
        Queue<PlayingCard> queue = new LinkedList<>();
        playingCards.forEach(queue::offer);

        Collections.shuffle((List<?>) queue);

        return new Deck(queue);
    }

    public PlayingCard drawn() {
        return playingCards.poll();
    }
}
