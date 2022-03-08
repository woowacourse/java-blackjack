package blackjack.domain.strategy;

import blackjack.domain.Card;

import java.util.List;

public interface CardStrategy {

    List<Card> generate();
}
