package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public abstract class Finished extends Started {

    Finished(CardBundle cardBundle) {
        super(cardBundle);
    }

    @Override
    public CardHand hit(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public CardHand stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
