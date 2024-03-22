package blackjack.domain.card.deck;

import blackjack.domain.card.Card;

import java.util.Deque;

public interface DeckCardGenerator {

    Deque<Card> generate();
}
