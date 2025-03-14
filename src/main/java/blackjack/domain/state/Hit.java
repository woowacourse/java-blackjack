package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.winning.WinningResult;

public class Hit extends Start {
    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.take(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public double profit(double bettingMoney) {
        throw new IllegalStateException("수익을 계산할 수 없습니다.");
    }

    @Override
    public WinningResult decide(State state) {
        throw new IllegalStateException("아직 승패를 결정할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
