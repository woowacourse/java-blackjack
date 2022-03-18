package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;

public abstract class Running extends Started {

    Running(final PlayingCards playingCards) {
        super(playingCards);
    }

    public State stay() {
        return new Stay(playingCards, betting);
    }

    public double profit() {
        throw new IllegalStateException("현재 상태는 수익을 계산할 수 없습니다.");
    }

    public boolean isFinished() {
        return false;
    }
}
