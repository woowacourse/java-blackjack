package domain.player;

import domain.Rull;
import domain.card.PlayerCards;
import domain.card.cardfactory.Card;

import java.util.List;
import java.util.Objects;

public class Player implements Gamer {
	private static final String ERROR_MESSAGE_NAME_BLANK = "이름은 공란이 될 수 없습니다.";

	protected String name;
	protected PlayerCards playerCards;

	protected Player(String name) {
		validateName(name);
		this.name = name;
		this.playerCards = new PlayerCards();
	}

	private void validateName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(ERROR_MESSAGE_NAME_BLANK);
		}
	}

	@Override
	public void cardDraw(List<Card> cards) {
		playerCards.addAll(cards);
	}

	@Override
	public int calculateScore() {
		return playerCards.calculateScore();
	}

	@Override
	public int calculateBurstIsZeroScore() {
		int score = playerCards.calculateScore();
		if (score > Rull.MAX_SCORE) {
			return Rull.BASE_SCORE;
		}
		return score;
	}

	public PlayerCards getPlayerCards() {
		return playerCards;
	}

	public String getName() {
		return this.name;
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
