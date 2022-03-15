package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public class Running implements BlackjackGameState {

    private final Cards cards;

    public Running(final Cards cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = cards;
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        final Cards newCards = cards.addCard(card);
        if (newCards.isBust()) {
            return new Bust(newCards);
        }
        return null;
    }

    @Override
    public BlackjackGameState stand() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<Card> cards() {
        return null;
    }

    @Override
    public int score() {
        throw new IllegalStateException("진행중인 상태는 스코어를 계산할 수 없습니다.");
    }
}
