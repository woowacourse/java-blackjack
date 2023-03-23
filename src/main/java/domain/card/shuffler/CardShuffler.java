package domain.card.shuffler;

import domain.card.Card;
import java.util.List;

@FunctionalInterface
public interface CardShuffler {

    void shuffleCards(List<Card> cards);
}
