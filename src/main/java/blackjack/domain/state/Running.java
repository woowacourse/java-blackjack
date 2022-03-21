package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;

public abstract class Running extends Started {

    Running(final PlayingCards playingCards) {
        super(playingCards);
    }

    public State stay() {
        return new Stay(playingCards, betting);
    }

    public double getEarning() {
        throw new IllegalStateException("현재 상태는 수익을 계산할 수 없습니다.");
    }

    public boolean isRunning() {
        return true;
    }

    public boolean isFinished() {
        return false;
    }

    public void decideRate(final double rate) {
        throw new IllegalStateException("현재 상태는 수익률을 변경할 수 없습니다.");
    }
}
