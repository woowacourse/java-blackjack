package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;

public class Blackjack extends Finished {

    public Blackjack(final PlayingCards playingCards) {
        super(playingCards);
    }
}
