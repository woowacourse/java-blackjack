package blackjack_statepattern.state;

import blackjack_statepattern.Cards;

public abstract class Running extends Started{

    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double money) {
        throw new IllegalArgumentException("수익을 구할 수 없습니다.");
    }
}
