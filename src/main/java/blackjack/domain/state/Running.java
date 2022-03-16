package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running extends AbstractBlackjackGameState {

    public Running(final Cards cards) {
        super(cards);
        checkBustCards(super.cards);
    }

    private void checkBustCards(final Cards cards) {
        if (cards.isBust()) {
            throw new IllegalArgumentException("Running상태는 버스트된 카드가 들어올 수 없습니다.");
        }
    }

    @Override
    public BlackjackGameState stay() {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Stand(cards, cards.score());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(final int betMoney, final BlackjackGameState blackjackGameState) {
        throw new IllegalStateException("Running상태는 수익을 계산할 수 없습니다.");
    }

    @Override
    public int score() {
        throw new IllegalStateException("Running상태는 score를 계산할 수 없습니다.");
    }
}
