package blackjack.blackjack.card.generator;

import blackjack.blackjack.card.Card;
import java.util.Deque;

public interface DeckGenerator {

    Deque<Card> makeDeck();
}
