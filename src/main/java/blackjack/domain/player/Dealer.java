package blackjack.domain.player;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.PlayingCard;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int GET_CARD_UPPER_BOUND = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean isFinished(final CardDeck cardDeck) {
        final PlayingCard peekedCard = cardDeck.justPeek();
        final int currentResult = playingCards.getResultWithPeekCard(peekedCard);
        return currentResult > GET_CARD_UPPER_BOUND;
    }

    @Override
    public void receiveCard(CardDeck cardDeck) {
        playingCards.addCard(cardDeck.pop());
    }
}
