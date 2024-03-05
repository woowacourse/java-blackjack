package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<PlayingCard> playingCards;

    private Hand(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }
}
