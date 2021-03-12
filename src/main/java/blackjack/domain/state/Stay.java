package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Stay extends State {

    public static final String STAY_DRAW_ERROR = "[Error] 스테이 상태에서는 드로우할 수 없습니다.";
    public static final String STAY_STAY_ERROR = "[Error] 스테이 상태에서는 스테이할 수 없습니다.";

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException(STAY_DRAW_ERROR);
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException(STAY_STAY_ERROR);
    }

    @Override
    public double earningRate() {
        return 1;
    }
}
