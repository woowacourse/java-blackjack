package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;

public class Stay extends Finished {

    public Stay(final PlayingCards playingCards) {
        super(playingCards);
    }
}
