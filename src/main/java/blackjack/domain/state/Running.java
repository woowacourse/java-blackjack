package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Running extends AbstractBlackjackGameState {

    public Running(final Cards cards) {
        super(cards);
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        final Cards newCards = cards.addCard(card);
        if (newCards.isBust()) {
            return new Bust(newCards);
        }
        return new Running(newCards);
    }

    @Override
    public BlackjackGameState stand() {
        return new Stand(Cards.copyOf(cards));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int score() {
        throw new IllegalStateException("진행중인 상태는 스코어를 계산할 수 없습니다.");
    }
}
