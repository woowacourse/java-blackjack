package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Bust extends State {

    public static final String BUST_DRAW_ERROR = "[Error] 버스트 상태에서는 드로우할 수 없습니다.";
    public static final String BUST_STAY_ERROR = "[Error] 버스트 상태에서는 스테이할 수 없습니다.";

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException(BUST_DRAW_ERROR);
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException(BUST_STAY_ERROR);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double earningRate() {
        return -1;
    }

}
