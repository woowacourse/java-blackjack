package domain.result;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.user.User;

public class ScoreBoard {
	private final User user;
	private final int score;

	private ScoreBoard(User user, int score) {
		this.user = Objects.requireNonNull(user);
		this.score = score;
	}

	public static ScoreBoard of(User user) {
		return new ScoreBoard(user, user.calculateScore());
	}

	public String getName() {
		return user.getName();
	}

	public List<Card> getCards() {
		return user.getCards();
	}

	public int getScore() {
		return score;
	}

	public MatchResult match(ScoreBoard dealerBoard) {
		return MatchResult.calculatePlayerMatchResult(user, Objects.requireNonNull(dealerBoard.user));
	}

	public PlayerResult createPlayerResult(ScoreBoard other) {
		return new PlayerResult(user, match(other));
	}
}
