package deck;

import java.util.List;
import card.Card;

public interface DeckCreateStrategy {

    List<Card> createAllCards();
}
