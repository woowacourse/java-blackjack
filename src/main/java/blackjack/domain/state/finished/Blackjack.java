package blackjack.domain.state.finished;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;

public class Blackjack extends Finished {

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalArgumentException("[ERROR] 블랙잭이므로 카드를 더 뽑을 수 없습니다.");
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
        return true;
    }

    @Override
    public Money profit(Money money) {
        return money.multiply(1.5);
    }
}
