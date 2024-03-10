package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardDrawStrategy;
import java.util.List;

abstract class TestAbstractFirstCardDrawStrategy implements CardDrawStrategy {

    @Override
    public final Card nextCard(List<Card> cards) {
        if (canDraw()) {
            return cardSelectStrategy(cards);
        }
        throw new IllegalStateException("카드를 더이상 뽑을 수 없습니다.");
    }

    abstract boolean canDraw();

    private Card cardSelectStrategy(List<Card> cards) {
        return cards.get(0);
    }
}
