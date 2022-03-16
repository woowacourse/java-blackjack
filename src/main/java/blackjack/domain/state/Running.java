package blackjack.domain.state;

import blackjack.domain.card.Cards;
import java.util.Objects;

public class Running implements BlackjackGameState {

    private final Cards cards;

    public Running(final Cards cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = cards;
        checkBustCards(cards);
    }

    private void checkBustCards(final Cards cards) {
        if (cards.isBust()) {
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
        return 0;
    }
}
