package domain.blackjack;

import domain.card.Card;
import domain.card.CardDrawStrategy;
import java.util.List;
import java.util.Random;

public abstract class AbstractRandomCardDrawStrategy implements CardDrawStrategy {
    @Override
    public final Card nextCard(List<Card> cards) {
        if (canDraw()) {
            return cardSelectStrategy(cards);
        }
        throw new IllegalStateException("카드를 더이상 뽑을 수 없습니다.");
    }

    public abstract boolean canDraw();

    private Card cardSelectStrategy(List<Card> cards) {
        Random random = new Random();
        int idx = random.nextInt(cards.size());
        return cards.get(idx);
    }
}
