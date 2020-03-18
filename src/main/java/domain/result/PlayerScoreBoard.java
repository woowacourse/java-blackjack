package domain.result;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.user.Player;

public class PlayerScoreBoard implements ScoreBoard {
	private final Player player;
	private final int score;

	private PlayerScoreBoard(Player player, int score) {
		this.player = Objects.requireNonNull(player);
		this.score = score;
	}

	public static PlayerScoreBoard of(Player player) {
		return new PlayerScoreBoard(player, player.calculateScore());
	}

	public String getName() {
		return player.getName();
	}

	public List<Card> getCards() {
		return player.getCards();
	}

	public int getScore() {
		return score;
	}

	private MatchResult match(DealerScoreBoard dealerBoard) {
		return MatchResult.calculatePlayerMatchResult(player, Objects.requireNonNull(dealerBoard.getUser()));
	}

	private int calculateMoney(DealerScoreBoard dealerBoard) {
		return match(dealerBoard).calculatePrize(player.getBettingMoney());
	}

	public UserResult createPlayerResult(DealerScoreBoard other) {
		return new UserResult(player, calculateMoney(other));
	}
}
