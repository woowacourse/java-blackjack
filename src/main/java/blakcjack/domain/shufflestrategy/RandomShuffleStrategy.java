package blakcjack.domain.shufflestrategy;

import blakcjack.domain.card.Card;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
	@Override
	public void shuffle(final List<Card> cards) {
		Collections.shuffle(cards);
	}
}
