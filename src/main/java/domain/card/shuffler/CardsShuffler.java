package domain.card.shuffler;

import domain.card.Card;
import java.util.Deque;

public interface CardsShuffler {

    Deque<Card> shuffleCards(Deque<Card> cards);
}
