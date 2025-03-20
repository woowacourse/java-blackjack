package blackjack.domain.card.generator;

import blackjack.domain.card.Card;
import java.util.Deque;

public interface DeckGenerator {

    Deque<Card> makeDeck();
}
