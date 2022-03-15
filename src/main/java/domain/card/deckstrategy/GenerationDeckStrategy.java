package domain.card.deckstrategy;

import java.util.Queue;

import domain.card.Card;

public interface GenerationDeckStrategy {
	Queue<Card> generateCardsForBlackJack();
}
