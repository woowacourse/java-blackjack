package domain.result;

import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

public class Score {
	private static final int ZERO = 0;
	public static final int TEN = 10;
	public static final int BLACKJACK_SCORE = 21;

	public static int calculate(List<Card> cards) {
		int rawScore = calculateRaw(cards);
		if (containAce(cards) && rawScore + TEN <= BLACKJACK_SCORE) {
			return rawScore + TEN;
		}
		if (rawScore > BLACKJACK_SCORE)
			return ZERO;
		return rawScore;
	}

	private static int calculateRaw(List<Card> cards) {
		return cards.stream()
			.map(Card::getSymbol)
			.map(Symbol::getScore)
			.reduce(ZERO, Integer::sum);
	}

	private static boolean containAce(List<Card> cards) {
		return cards.stream()
			.map(Card::getSymbol)
			.anyMatch(symbol -> symbol == Symbol.ACE);
	}
}
