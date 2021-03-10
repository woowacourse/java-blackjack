package blackjack.domain.state;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public abstract class Finished extends Started {
    private static final String CANNOT_DRAW_CARD_ERROR_MESSAGE = "카드를 뽑을 수 없는 상태입니다.";

    public Finished(Cards cards) {
        super(cards);
    }

    public abstract BigDecimal rate();

    @Override
    public BigDecimal profit(BettingMoney money) {
        return money.multiply(rate());
    }

    public boolean isWin(State state) {
        return this.cards.calculateScore() > state.getCards().calculateScore();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException(CANNOT_DRAW_CARD_ERROR_MESSAGE);
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }
}
