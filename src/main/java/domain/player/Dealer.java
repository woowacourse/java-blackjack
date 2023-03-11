package domain.player;

import domain.card.BlackJackScore;
import domain.card.Card;
import domain.card.CardArea;
import domain.card.CardDeck;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = Name.of("딜러");
    private static final BlackJackScore DEALER_DONT_HIT_SCORE = BlackJackScore.of(17);

    public Dealer(final CardArea cardArea) {
        super(DEALER_NAME, cardArea);
    }

    @Override
    public boolean canHit() {
        return DEALER_DONT_HIT_SCORE.isLargerThan(score());
    }

    @Override
    public boolean hitOrStay(final CardDeck cardDeck) {
        if (canHit()) {
            hit(cardDeck);
            return true;
        }
        return false;
    }

    public Card firstCard() {
        return cardArea.firstCard();
    }
}
