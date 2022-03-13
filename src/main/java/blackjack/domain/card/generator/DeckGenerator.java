package blackjack.domain.card.generator;

import java.util.List;

import blackjack.domain.card.Card;

public interface DeckGenerator {

    List<Card> generate();

}
