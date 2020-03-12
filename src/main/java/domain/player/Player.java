package domain.player;

import domain.Rull;
import domain.card.PlayerCards;
import domain.card.cardfactory.Card;

import java.util.List;
import java.util.Objects;

public class Player implements PlayerInterface {
	private static final String STRING_FORMAT_PRINT_CARD = "%s카드 : %s";

	protected String name;
	protected PlayerCards playerCards;

	public void cardDraw(List<Card> cards) {
		playerCards.addAll(cards);
	}

	public String getName() {
		return this.name;
	}

	public int calculateScore() {
		return playerCards.calculateScore();
	}

	public int calculateBurstIsZeroScore() {
		int score = playerCards.calculateScore();
		if (score > Rull.MAX_SCORE) {
			return 0;
		}
		return score;
	}

	public String toStringAllCard() {
		return String.format(STRING_FORMAT_PRINT_CARD, this.name, playerCards.toStringAllCard());
	}

	public String toStringOneCard() {
		return String.format(STRING_FORMAT_PRINT_CARD, this.name, playerCards.toStringOneCard());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(name, user.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
