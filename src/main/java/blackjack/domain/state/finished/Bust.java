package blackjack.domain.state.finished;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;

public class Bust extends Finished {

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalArgumentException("[ERROR] 버스트이므로 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public Money profit(Money money) {
        return money;
    }
}
