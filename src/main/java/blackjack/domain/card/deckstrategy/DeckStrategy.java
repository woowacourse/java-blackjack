package blackjack.domain.card.deckstrategy;

import java.util.Deque;

import blackjack.domain.card.Card;

public interface DeckStrategy {

    Deque<Card> create();
}
