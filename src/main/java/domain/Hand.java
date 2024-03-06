package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.constant.GameOption.BLACKJACK_CONDITION;

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

    public boolean isBurst() {
        return getCardsNumberSum() > BLACKJACK_CONDITION;
    }
}
