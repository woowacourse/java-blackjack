package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public final class OneCard extends Running {

    private final static String CAN_NOT_FINISH_EXCEPTION_MESSAGE = "아직 한 장밖에 없습니다.";

    public OneCard(Card card) {
        super(new CardBundle(card));
    }

    @Override
    public CardHand hit(Card card) {
        CardBundle newCardBundle = cardBundle.add(card);
        if (newCardBundle.isBlackjack()) {
            return new Blackjack(newCardBundle);
        }
        return new CanHit(newCardBundle);
    }

    @Override
    public CardHand stay() {
        throw new IllegalStateException(CAN_NOT_FINISH_EXCEPTION_MESSAGE);
    }

    @Override
    public String toString() {
        return "OneCard{" + "cardBundle=" + cardBundle + '}';
    }
}
