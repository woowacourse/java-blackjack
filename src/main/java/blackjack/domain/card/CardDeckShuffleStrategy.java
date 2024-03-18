package blackjack.domain.card;

import java.util.List;

@FunctionalInterface
public interface CardDeckShuffleStrategy {

    List<Card> shuffle(List<Card> cards);
}
