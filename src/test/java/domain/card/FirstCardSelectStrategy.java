package domain.card;

import java.util.List;

public class FirstCardSelectStrategy implements CardSelectStrategy {

    public static final FirstCardSelectStrategy FIRST_CARD_SELECT_STRATEGY = new FirstCardSelectStrategy();

    @Override
    public Card select(List<Card> cards) {
        return cards.get(0);
    }
}
