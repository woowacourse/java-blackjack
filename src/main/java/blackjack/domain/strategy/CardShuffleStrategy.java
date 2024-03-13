package blackjack.domain.strategy;

import blackjack.domain.card.Card;

import java.util.List;

@FunctionalInterface
public interface CardShuffleStrategy {

    void shuffle(List<Card> cards);
}
