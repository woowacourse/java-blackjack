package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.utils.Constants;

public final class Ready extends Running {

    public Ready(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public State draw(final Card card) {
        final CardHand cardHand = receiveCard(card);

        if (cardHand.countCards() < Constants.INITIAL_CARD_COUNT) {
            return new Ready(cardHand);
        }
        if (cardHand.isBlackjack()) {
            return new Blackjack(cardHand);
        }
        return new Hit(cardHand);
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("초기 카드를 전부 받지 않았습니다.");
    }
}
