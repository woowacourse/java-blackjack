package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.List;

public interface CardShuffleStrategy {
    List<Card> shuffle(List<Card> cards);
}
