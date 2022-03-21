package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;

public class Bust extends Finished {

    public Bust(final PlayingCards playingCards) {
        super(playingCards);
    }
}
