package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;

public class Dealer extends Participant {

    private static final int LIMIT_SCORE = 17;

    private final CardDeck cardDeck;

    public Dealer() {
        this.cardDeck = CardDeck.newShuffledDeck();
    }

    public Card drawCard() {
        return cardDeck.draw();
    }

    @Override
    public boolean isOverLimitScore() {
        return getTotalScore() >= LIMIT_SCORE;
    }
}
