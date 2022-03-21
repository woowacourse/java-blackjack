package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;

public abstract class Running extends Started {

    Running(final PlayingCards playingCards) {
        super(playingCards);
    }

    public State stay() {
        return new Stay(playingCards);
    }

    public boolean isRunning() {
        return true;
    }

    public boolean isFinished() {
        return false;
    }

    public boolean isBlackjack() {
        return false;
    }

    public boolean isBust() {
        return false;
    }

    public void decideRate(final double rate) {
        throw new IllegalStateException("현재 상태는 수익률을 변경할 수 없습니다.");
    }

    public double getEarningRate() {
        throw new IllegalStateException("현재 상태는 수익률을 구할 수 없습니다.");
    }
}
