package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

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
        throw new IllegalArgumentException("[ERROR] 게임이 끝나야 수익을 계산할 수 있습니다.");
    }
}
