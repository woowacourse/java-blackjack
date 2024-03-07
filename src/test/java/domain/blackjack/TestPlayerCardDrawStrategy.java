package domain.blackjack;

import domain.blackjack.Gamer;
import domain.card.Card;
import domain.card.CardDrawStrategy;
import java.util.List;

public class TestPlayerCardDrawStrategy implements CardDrawStrategy {
    private final Gamer player;

    public TestPlayerCardDrawStrategy(Gamer player) {
        this.player = player;
    }

    @Override
    public final Card nextCard(List<Card> cards) {
        if (canDraw()) {
            return cardSelectStrategy(cards);
        }
        throw new IllegalStateException("카드를 더이상 뽑을 수 없습니다.");
    }

    private boolean canDraw() {
        return !player.getSummationCardPoint().isDeadPoint();
    }

    private Card cardSelectStrategy(List<Card> cards) {
        return cards.get(0);
    }
}
