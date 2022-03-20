package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.playerstatus.Blackjack;
import blackjack.domain.participant.playerstatus.Bust;
import blackjack.domain.participant.playerstatus.Stay;

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
            playerStatus = Blackjack.getInstance();
            return;
        }
        if (cards.isBust()) {
            playerStatus = Bust.getInstance();
            return;
        }
        playerStatus = Stay.getInstance();
    }

    public Card openFirstCard() {
        return getCards().findFirst();
    }

    @Override
    public String getName() {
        return NAME;
    }
}
