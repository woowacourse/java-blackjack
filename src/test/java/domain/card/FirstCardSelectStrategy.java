package domain.card;

import java.util.List;

public class FirstCardSelectStrategy implements CardSelectStrategy {
    @Override
    public Card select(List<Card> cards) {
        return cards.get(0);
    }
}
