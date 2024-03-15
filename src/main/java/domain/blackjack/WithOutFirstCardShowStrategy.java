package domain.blackjack;

import domain.card.Card;
import java.util.List;

public class WithOutFirstCardShowStrategy implements CardShowStrategy {
    public static WithOutFirstCardShowStrategy INSTANCE = new WithOutFirstCardShowStrategy();

    private WithOutFirstCardShowStrategy() {
    }

    @Override
    public List<Card> show(List<Card> allCards) {
        return allCards.subList(1, allCards.size());
    }
}
