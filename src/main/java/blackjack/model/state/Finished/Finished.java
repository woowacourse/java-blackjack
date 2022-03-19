package blackjack.model.state.Finished;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.state.Started.Started;
import blackjack.model.state.State;

public abstract class Finished extends Started {

    protected Finished(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public State add(Card card) {
        throw new IllegalArgumentException("[ERROR] 카드를 추가 할 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("[ERROR] 이미 카드 분배가 끝났습니다.");
    }
}
