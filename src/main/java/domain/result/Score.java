package domain.result;

import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

public class Score {
	private static final int ZERO = 0;

	public static int calculate(List<Card> cards) {
		return cards.stream()
			.map(Card::getSymbol)
			.map(Symbol::getScore)
			.reduce(ZERO, Integer::sum);
	}
}
