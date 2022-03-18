package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public final class CanHit extends Running {

    CanHit(CardBundle cardBundle) {
        super(cardBundle);
    }

    @Override
    public CardHand hit(Card card) {
        CardBundle newCardBundle = cardBundle.add(card);
        if (newCardBundle.isBust()) {
            return new Bust(newCardBundle);
        }
        return new CanHit(newCardBundle);
    }

    @Override
    public CardHand stay() {
        return new Stay(cardBundle);
    }

    @Override
    public String toString() {
        return "CanHit{" + "cardBundle=" + cardBundle + '}';
    }
}
