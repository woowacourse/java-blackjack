package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<PlayingCard> playingCards;

    Hand(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }

    public int getCardsNumberSum() {
        return playingCards.stream()
                .mapToInt(card -> card.addValue(0))
                .sum();
    }
}
