package domain.card;

import java.util.List;

public class Cards {
	private final List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = cards;
	}

	public int calculateScore() {
		int result = cards.stream()
			.map(Card::getScore)
			.reduce(Integer::sum)
			.orElseThrow(() -> new IllegalArgumentException());
		//ACE 있는지 && 있으면, 점수 + 10점 <=21 인지 맞으면, 10점 플러스
		if (notContainsAce()) {
			return result;
		}
		if (result + 10 <= 21) {
			return result + 10;
		}
		return result;
	}

	private boolean notContainsAce() {
		for (Card card : cards) {

		}
		return false;
	}
}
