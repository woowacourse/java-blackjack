package domain.deck;

import domain.card.Card;
import java.util.List;

public class PlayerDeck extends UserDeck {
    @Override
    public boolean isAddable() {
        return !busted();
    }

    @Override
    public List<Card> getVisibleCards() {
        return getCards();
    }
}
