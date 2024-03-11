package domain.deck;

import domain.card.Card;
import java.util.List;

public class DealerDeck extends UserDeck {
    private final static int ADD_CONDITION = 16;
    private static final int FIRST_INDEX = 0;

    @Override
    public boolean isAddable() {
        return sumCard() <= ADD_CONDITION;
    }

    @Override
    public List<Card> getVisibleCards() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 존재하지 않는 것은 불가합니다.");
        }
        return List.of(cards.get(FIRST_INDEX));
    }
}
