package domain.strategy;

import java.util.Collections;
import java.util.List;

import domain.card.Card;

public class RandomShuffleStrategy implements ShuffleStrategy {

	@Override
	public void shuffle(List<Card> cards) {
		Collections.shuffle(cards);
	}
}
