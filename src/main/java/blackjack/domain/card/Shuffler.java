package blackjack.domain.card;

import blackjack.domain.card.Card;
import java.util.List;

@FunctionalInterface
public interface Shuffler {
    List<Card> shuffle(List<Card> cards);
}
