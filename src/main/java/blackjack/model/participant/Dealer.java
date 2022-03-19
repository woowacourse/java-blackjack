package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.view.GameSign;
import blackjack.view.TurnProgress;

public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final int DEALER_HIT_LIMIT_SCORE = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public void hitFrom(CardDeck cardDeck, GameSign gameSign, TurnProgress turnProgress) {
        while (this.state.isHit() && isNotStay()) {
            this.state = this.state.add(cardDeck.draw());
        }
        turnProgress.show(this);
    }

    private boolean isNotStay() {
        if (isFinished()) {
            this.state = this.state.stay();
            return false;
        }
        return true;
    }

    private boolean isFinished() {
        return this.state.getScore() > DEALER_HIT_LIMIT_SCORE;
    }
}
