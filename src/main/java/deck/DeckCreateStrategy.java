package deck;

import card.Card;
import java.util.List;

public interface DeckCreateStrategy {

    List<Card> createAllCards();
}
