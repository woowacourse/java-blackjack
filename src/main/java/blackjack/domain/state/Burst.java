package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Burst extends Finished {
    public static final String EXCEPTION_OVER_21 = "21을 초과하여 더이상 카드를 받을 수 없습니다.";

    public Burst(Cards cards) {
        super(cards);
    }

    @Override
    public State takeCard(Card card) {
        throw new IllegalArgumentException(EXCEPTION_OVER_21);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBurst() {
        return true;
    }



}
