package domain.blackjack;

import domain.card.Card;
import java.util.List;

public class AllCardShowStrategy implements CardShowStrategy {
    public static AllCardShowStrategy INSTANCE = new AllCardShowStrategy();

    private AllCardShowStrategy() {
    }

    @Override
    public List<Card> show(List<Card> allCards) {
        return List.copyOf(allCards);
    }
}
