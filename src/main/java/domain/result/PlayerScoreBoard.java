package domain.result;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.score.Score;
import domain.user.Player;

public class PlayerScoreBoard implements ScoreBoard {
	private final Player player;
	private final Score score;

	public PlayerScoreBoard(Player player, Score score) {
		this.player = Objects.requireNonNull(player);
		this.score = Objects.requireNonNull(score);
	}

	public static PlayerScoreBoard of(Player player) {
		return new PlayerScoreBoard(player, player.calculateScore());
	}

	public UserResult createPlayerResult(DealerScoreBoard other) {
		return new UserResult(player, calculatePrize(other));
	}

	private Prize calculatePrize(DealerScoreBoard dealerBoard) {
		return match(dealerBoard).calculatePrize(player.getBettingMoney());
	}

	private MatchResult match(DealerScoreBoard dealerBoard) {
		return MatchResult.calculatePlayerMatchResult(player, Objects.requireNonNull(dealerBoard.getUser()));
	}

	public String getName() {
		return player.getName();
	}

	public List<Card> getCards() {
		return player.getCards();
	}

	public Score getScore() {
		return score;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		PlayerScoreBoard that = (PlayerScoreBoard)object;
		return this.score == that.score &&
			Objects.equals(this.player, that.player);
	}

	@Override
	public int hashCode() {
		return Objects.hash(player, score);
	}
}
