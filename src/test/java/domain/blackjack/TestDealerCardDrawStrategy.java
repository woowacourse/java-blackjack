package domain.blackjack;

import domain.card.Card;
import domain.card.CardDrawStrategy;
import java.util.List;

public class TestDealerCardDrawStrategy implements CardDrawStrategy {
    private final Gamer player;

    public TestDealerCardDrawStrategy(Gamer player) {
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
        return !player.getSummationCardPoint().isBiggerThan(new SummationCardPoint(16));
    }

    private Card cardSelectStrategy(List<Card> cards) {
        return cards.get(0);
    }
}
