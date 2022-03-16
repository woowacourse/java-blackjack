package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class Running implements BlackjackGameState {

    private final Cards cards;

    public Running(final Cards cards) {
        checkBustCards(cards);
        this.cards = cards;
    }

    private void checkBustCards(final Cards cards) {
        if (cards.isBust()) {
            throw new IllegalArgumentException("Running상태는 버스트된 카드가 들어올 수 없습니다.");
        }
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        cards.addCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Running(cards);
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
