package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public abstract class Finished extends Started {

    Finished(CardBundle cardBundle) {
        super(cardBundle);
    }

    @Override
    public final CardHand hit(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public final CardHand stay() {
        throw new IllegalStateException();
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
