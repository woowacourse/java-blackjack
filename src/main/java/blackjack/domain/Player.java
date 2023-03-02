package blackjack.domain;

import static blackjack.domain.BlackJackConstant.BLACKJACK;

public class Player extends Participant {

    protected Player(final CardPocket cardPocket) {
        super(cardPocket);
    }

    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore < BLACKJACK;
    }
}
