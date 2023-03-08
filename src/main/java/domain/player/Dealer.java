package domain.player;

import domain.card.Card;
import domain.card.CardArea;
import domain.game.Revenue;

import static domain.player.GamblerCompeteResult.*;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = Name.of("딜러");

    public Dealer(final CardArea cardArea) {
        super(DEALER_NAME, cardArea);
    }

    @Override
    public boolean canHit() {
        return score().isDealerShouldHitScore();
    }

    public Card firstCard() {
        return cardArea.firstCard();
    }

    public Revenue compete(final Gambler gambler) {
        if (isGamblerWin(gambler)) {
            return revenueForWin(gambler);
        }
        if (gambler.isBust()) {
            return LOSE.revenue(gambler);
        }
        if (isLargerScoreThan(gambler)) {
            return LOSE.revenue(gambler);
        }
        return DRAW.revenue(gambler);
    }

    private boolean isGamblerWin(final Gambler gambler) {
        if (gambler.isBust()) {
            return false;
        }
        return isBust() || gambler.isLargerScoreThan(this);
    }

    private Revenue revenueForWin(final Gambler gambler) {
        if (gambler.isBlackJack()) {
            return BLACK_JACK_WIN.revenue(gambler);
        }
        return WIN.revenue(gambler);
    }
}
