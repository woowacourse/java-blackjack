package domain;

import domain.card.Card;
import java.util.List;

public interface DeckShuffler {
    List<Card> shuffle(List<Card> cards);
}
