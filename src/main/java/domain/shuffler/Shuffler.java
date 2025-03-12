package domain.shuffler;

import domain.card.Card;
import java.util.List;

public interface Shuffler {
    void shuffle(List<Card> cards);
}
