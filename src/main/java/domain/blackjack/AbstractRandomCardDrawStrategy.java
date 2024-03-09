package domain.blackjack;

import domain.card.Card;
import domain.card.CardDrawStrategy;
import java.util.List;
import java.util.Random;

abstract class AbstractRandomCardDrawStrategy implements CardDrawStrategy {
    private final Random random = new Random();

    @Override
    public final Card nextCard(List<Card> cards) {
        if (canDraw()) {
            return cardSelectStrategy(cards);
        }
        throw new IllegalStateException("카드를 더이상 뽑을 수 없습니다.");
    }

    abstract boolean canDraw();

    private Card cardSelectStrategy(List<Card> cards) {
        int idx = random.nextInt(cards.size());
        return cards.get(idx);
    }
}
