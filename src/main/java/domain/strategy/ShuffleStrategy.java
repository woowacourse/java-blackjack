package domain.strategy;

import java.util.List;

import domain.card.Card;

public interface ShuffleStrategy {
	void shuffle(List<Card> cards);
}
