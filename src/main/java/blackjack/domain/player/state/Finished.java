package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.player.Hand;

public abstract class Finished extends PlayerStatus {
    protected final int NEGATIVE = -1;

    Finished(Hand hand) {
        super(hand);
    }

    @Override
    public PlayerStatus draw(Card card) {
        throw new IllegalStateException("추가로 카드를 받을 수 없습니다.");
    }

    @Override
    public PlayerStatus stay() {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
