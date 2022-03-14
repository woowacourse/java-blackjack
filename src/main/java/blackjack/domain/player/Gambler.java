package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class Gambler extends Player {

    public Gambler(final String name) {
        super(name);
    }

    @Override
    public boolean isFinished(final CardDeck cardDeck, final int getCardUpperBound) {
        playingCards.addCard(cardDeck.pop());
        return playingCards.getCardSum() > getCardUpperBound;
    }
}
