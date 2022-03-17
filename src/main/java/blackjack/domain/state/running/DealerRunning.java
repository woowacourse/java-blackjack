package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stand;

public final class DealerRunning extends Running {

    private DealerRunning(final Cards cards) {
        super(cards);
    }

    public static BlackjackGameState createDealerGameState(final Cards cards) {
        if (cards.isMaxScoreBust()) {
            return new Bust(cards, cards.maxScore());
        }
        if (cards.isDealerStandScore()) {
            return new Stand(cards, cards.maxScore());
        }
        return new DealerRunning(cards);
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        cards.addCard(card);
        return createDealerGameState(cards);
    }

    @Override
    public BlackjackGameState stay() {
        throw new IllegalStateException("딜러의 running 상태는 stay를 할 수 없습니다.");
    }
}
