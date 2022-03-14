package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class Dealer extends Player {

    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean isFinished(final CardDeck cardDeck, final int getCardUpperBound) {
        return playingCards.getCardSum() > getCardUpperBound;
    }
}
