package domain;

import domain.card.Card;
import java.util.List;

public interface DeckShuffler {
    void shuffle(List<Card> cards);
}
