package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Running extends AbstractBlackjackGameState {

    public Running(final Cards cards) {
        super(cards);
        checkBustCards();
    }

    private void checkBustCards() {
        if (isBust()) {
            throw new IllegalArgumentException("Running상태는 버스트된 카드가 들어올 수 없습니다.");
        }
    }

    @Override
    public BlackjackGameState hit() {
        return null;
    }

    @Override
    public BlackjackGameState stay() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earningRate(final BlackjackGameState blackjackGameState) {
        throw new IllegalStateException("Running상태는 수익률을 계산할 수 없습니다.");
    }
}
