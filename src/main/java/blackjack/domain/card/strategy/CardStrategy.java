package blackjack.domain.card.strategy;

import blackjack.domain.card.Card;

import java.util.List;

public interface CardStrategy {

    List<Card> generate();
}
