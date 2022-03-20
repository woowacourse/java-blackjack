package blackjack.domain.state;

import blackjack.domain.card.PlayingCards;

public abstract class Started implements State {

    private final PlayingCards playingCards;

    public Started(PlayingCards playingCards) {
        this.playingCards = playingCards;
    }

    @Override
    public final PlayingCards playingCards() {
        return playingCards;
    }
}
