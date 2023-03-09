package domain.shuffler;

import domain.Card;
import java.util.List;

@FunctionalInterface
public interface CardShuffler {

    void shuffleCards(List<Card> cards);
}
