package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int GET_CARD_UPPER_BOUND = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean isFinished(final CardDeck cardDeck) {
        playingCards.addCard(cardDeck.pop());
        return playingCards.getCardSum() > GET_CARD_UPPER_BOUND;
    }

    @Override
    public void receiveCard(CardDeck cardDeck) {
        playingCards.addCard(cardDeck.pop());
    }
}
