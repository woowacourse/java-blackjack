package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public abstract class Finished implements State {

    private static final String FINISHED_ERROR = "[ERROR] 더 이상 카드를 받을 수 없습니다.";

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(UserDeck userDeck) {
        throw new IllegalStateException(FINISHED_ERROR);
    }

    public abstract double earningRate();
}
