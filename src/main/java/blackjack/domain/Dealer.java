package blackjack.domain;

import blackjack.domain.strategy.NumberGenerator;

public class Dealer extends Person {
	private static final String NAME = "딜러";
	private static final int CONDITION_HIT = 16;
	private final Cards cards;

	public Dealer() {
		super(NAME);
		cards = new Cards();
	}

	public Card handOutCard(NumberGenerator numberGenerator) {
		return cards.pickCard(numberGenerator);
	}

	public boolean isHit() {
		return Score.from(myCards).
			getSum() <= CONDITION_HIT;
	}

	public int isWin(Player player) {
		if(isBurst()) {
			if (player.isBurst()) {
				return 1;
			}

			return -1;
		}

		if(player.isBurst()) {
			return 1;
		}

		return Integer.compare(Score.from(myCards).getSum(),
			Score.from(player.myCards).getSum());
	}
}
