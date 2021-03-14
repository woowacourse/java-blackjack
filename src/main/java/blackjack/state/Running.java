package blackjack.state;

import blackjack.domain.User;

public abstract class Running extends Started {

    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double money, User dealer) {
        throw new UnsupportedOperationException("게임 진행이 끝나지 않아서 결과를 낼 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
