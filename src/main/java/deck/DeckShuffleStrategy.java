package deck;

import card.Card;
import java.util.List;

public interface DeckShuffleStrategy {

    List<Card> createAllCards();
}
