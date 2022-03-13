package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class Gambler extends Player {

    private static final int BURST_CRITERIA = 21;

    public Gambler(final String name) {
        super(name);
    }

    @Override
    public boolean isFinished(final CardDeck cardDeck) {
        playingCards.addCard(cardDeck.pop());
        return playingCards.getCardSum() > BURST_CRITERIA;
    }

    @Override
    public int getSumOfCards() {
        return super.getSumOfCards();
    }
}
