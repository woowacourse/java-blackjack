package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public class Created implements State {
    @Override
    public State draw(Card card) {
        throw new IllegalStateException("카드를 아직 가질 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("아직 시작전이라 스테이할 수 없습니다.");
    }

    @Override
    public double profit(Cards dealerCards, double bettingMoney) {
        throw new IllegalStateException("수익을 계산할 수 없습니다.");
    }

    @Override
    public Score calculateTotalScore() {
        throw new IllegalStateException("점수를 계산할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Cards cards() {
        throw new IllegalStateException("존재하는 카드가 없습니다.");
    }
}
