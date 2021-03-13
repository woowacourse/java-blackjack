package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Bust extends Started {
    public static final int PROFIT_RATE = -1;

    public Bust(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
    }

    @Override
    public double profit(int betMoney) {
        return betMoney * PROFIT_RATE;
    }
}
