package blackjack.domain.card.strategy;

import java.util.List;

import blackjack.domain.card.Card;

public interface DeckGenerator {

    List<Card> generate();

}
