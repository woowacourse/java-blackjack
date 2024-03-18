package blackjack.domain.deck;

import java.util.Deque;

public interface DeckCreateStrategy {

    Deque<Card> createDeck();
}
