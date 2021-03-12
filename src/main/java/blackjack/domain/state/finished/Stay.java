package blackjack.domain.state.finished;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;

public class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalArgumentException("[ERROR] 스테이이므로 더 이상 카드를 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
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
