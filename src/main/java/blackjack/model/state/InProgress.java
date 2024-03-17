package blackjack.model.state;

import blackjack.model.cards.Cards;
import blackjack.model.results.Result;

public abstract class InProgress implements State {
    private final Cards cards;

    public InProgress(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Result determineResult(State otherState) {
        throw new UnsupportedOperationException("게임 종료 전에는 승패를 계산할 수 없습니다.");
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public int getScore() {
        return cards.getScoreValue();
    }
}
