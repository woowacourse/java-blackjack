package domain;

import java.util.ArrayList;
import java.util.List;

public class Hands {
	private List<Card> hands;

	public Hands() {
		hands = new ArrayList<>();
	}

	public int calculateTotalScore() {
		return hands.stream()
			.mapToInt(Card::score)
			.sum();
	}

	public boolean hasAce() {
		return hands.stream()
			.anyMatch(Card::isAce);
	}

	public void add(Card card) {
		hands.add(card);
	}

	public int getSize() {
		return hands.size();
	}
}
