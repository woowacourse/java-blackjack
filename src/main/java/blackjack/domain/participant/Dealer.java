package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Dealer extends Participant {

    private static final int SCORE_LOWER_BOUND = 17;
    private static final String NAME = "딜러";

    public Dealer() {
        super();
    }

    @Override
    public boolean isDrawable() {
        return getScore() < SCORE_LOWER_BOUND;
    }

    @Override
    public void hit(final Deck deck) {
        cards.add(deck.drawCard());
    }

    public void updateStatus() {
        if (checkBlackjack()) {
            playerStatus = PlayerStatus.BLACKJACK;
            return;
        }
        if (cards.isBust()) {
            playerStatus = PlayerStatus.BUST;
            return;
        }
        playerStatus = PlayerStatus.STAY;
    }

    public Card openFirstCard() {
        return getCards().findFirst();
    }

    @Override
    public String getName() {
        return NAME;
    }
}
