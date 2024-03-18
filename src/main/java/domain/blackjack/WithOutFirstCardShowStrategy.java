package domain.blackjack;

import domain.card.Card;
import java.util.List;

public class WithOutFirstCardShowStrategy implements CardShowStrategy {
    public static WithOutFirstCardShowStrategy INSTANCE = new WithOutFirstCardShowStrategy();

    private WithOutFirstCardShowStrategy() {
    }

    @Override
    public List<Card> showSub(List<Card> cards) {
        return cards.subList(1, cards.size());
    }
}
