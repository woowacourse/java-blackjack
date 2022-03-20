package blackjack.domain.hand;

import blackjack.domain.card.CardBundle;

public abstract class Started implements CardHand {

    protected final CardBundle cardBundle;

    protected Started(CardBundle cardBundle) {
        this.cardBundle = cardBundle;
    }

    @Override
    public final CardBundle getCardBundle() {
        return cardBundle;
    }
}
