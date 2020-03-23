package domain.player;

import domain.Rule;
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
		if (score > Rule.MAX_SCORE) {
			return Rule.BASE_SCORE;
		}
		return score;
	}

	@Override
	public boolean isBlackJack() {
		return playerCards.calculateScore() == Rule.MAX_SCORE;
	}

	@Override
	public boolean nonBlackJack() {
		return playerCards.calculateScore() != Rule.MAX_SCORE;
	}

	public PlayerCards getPlayerCards() {
		return this.playerCards;
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
