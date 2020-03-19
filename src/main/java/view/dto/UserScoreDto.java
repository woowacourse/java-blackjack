package view.dto;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.score.Score;
import domain.user.User;

public class UserScoreDto {
	private final User user;
	private final Score score;

	public UserScoreDto(User user, Score score) {
		this.user = Objects.requireNonNull(user);
		this.score = Objects.requireNonNull(score);
	}

	public String getName() {
		return user.getName();
	}

	public Score getScore() {
		return score;
	}

	public List<Card> getCards() {
		return user.getCards();
	}
}
