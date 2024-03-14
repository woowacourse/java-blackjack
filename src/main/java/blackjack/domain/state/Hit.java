package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.game.ResultStatus;

public final class Hit extends Started {

    public Hit(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public State draw(final Card card) {
        final CardHand cardHand = getCardHand();
        cardHand.receiveCard(card);

        if (cardHand.isBlackjack()) {
            return new Blackjack(cardHand);
        }
        if (cardHand.isBust()) {
            return new Bust(cardHand);
        }
        return new Hit(cardHand);
    }

    @Override
    public State stay() {
        return new Stay(getCardHand());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        throw new UnsupportedOperationException("아직 게임이 종료되지 않아 수익률을 계산할 수 없습니다.");
    }
}
