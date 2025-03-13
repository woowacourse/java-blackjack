package domain.shuffler;

import domain.card.Card;
import java.util.Deque;

public interface Shuffler {
    void shuffle(Deque<Card> cards);
}
