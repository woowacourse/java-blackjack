package blackjack.domain.state;

import blackjack.domain.card.Card;

public class Finished extends State{
    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("결과가 정해졌기에 더 이상 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("결과가 정해졌기에 stay 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
