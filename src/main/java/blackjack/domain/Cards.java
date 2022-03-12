package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.strategy.NumberGenerator;

public class Cards {
	private static final int SIZE = 48;

	public static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	private static final int NINE = 9;
	private static final int TEN = 10;

	private static final List<Card> cards;

	static {
		cards = new ArrayList<>();
		cards.add(new Card("A다이아몬드", ONE));
		cards.add(new Card("2다이아몬드", TWO));
		cards.add(new Card("3다이아몬드", THREE));
		cards.add(new Card("4다이아몬드", FOUR));
		cards.add(new Card("5다이아몬드", FIVE));
		cards.add(new Card("6다이아몬드", SIX));
		cards.add(new Card("7다이아몬드", SEVEN));
		cards.add(new Card("8다이아몬드", EIGHT));
		cards.add(new Card("9다이아몬드", NINE));
		cards.add(new Card("J다이아몬드", TEN));
		cards.add(new Card("Q다이아몬드", TEN));
		cards.add(new Card("K다이아몬드", TEN));

		cards.add(new Card("A하트", ONE));
		cards.add(new Card("2하트", TWO));
		cards.add(new Card("3하트", THREE));
		cards.add(new Card("4하트", FOUR));
		cards.add(new Card("5하트", FIVE));
		cards.add(new Card("6하트", SIX));
		cards.add(new Card("7하트", SEVEN));
		cards.add(new Card("8하트", EIGHT));
		cards.add(new Card("9하트", NINE));
		cards.add(new Card("J하트", TEN));
		cards.add(new Card("Q하트", TEN));
		cards.add(new Card("K하트", TEN));

		cards.add(new Card("A스페이드", ONE));
		cards.add(new Card("2스페이드", TWO));
		cards.add(new Card("3스페이드", THREE));
		cards.add(new Card("4스페이드", FOUR));
		cards.add(new Card("5스페이드", FIVE));
		cards.add(new Card("6스페이드", SIX));
		cards.add(new Card("7스페이드", SEVEN));
		cards.add(new Card("8스페이드", EIGHT));
		cards.add(new Card("9스페이드", NINE));
		cards.add(new Card("J스페이드", TEN));
		cards.add(new Card("Q스페이드", TEN));
		cards.add(new Card("K스페이드", TEN));

		cards.add(new Card("A클로버", ONE));
		cards.add(new Card("2클로버", TWO));
		cards.add(new Card("3클로버", THREE));
		cards.add(new Card("4클로버", FOUR));
		cards.add(new Card("5클로버", FIVE));
		cards.add(new Card("6클로버", SIX));
		cards.add(new Card("7클로버", SEVEN));
		cards.add(new Card("8클로버", EIGHT));
		cards.add(new Card("9클로버", NINE));
		cards.add(new Card("J클로버", TEN));
		cards.add(new Card("Q클로버", TEN));
		cards.add(new Card("K클로버", TEN));
	}

	private List<Boolean> isUsed;

	public Cards() {
		isUsed = new ArrayList<>();
		for (int i = 0; i < SIZE; ++i) {
			isUsed.add(false);
		}
	}

	public Card pickCard(NumberGenerator numberGenerator) {
		int index = numberGenerator.generateNumber();
		while (isDuplicated(index)) {
			index = numberGenerator.generateNumber();
		}

		isUsed.set(index, true);
		return cards.get(index);
	}

	private boolean isDuplicated(int index) {
		return isUsed.get(index);
	}
}
