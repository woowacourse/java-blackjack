package domain.result;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.Player;

public class ScoreBoards {
	private final List<ScoreBoard> playerBoards;
	private final ScoreBoard dealerBoard;

	private ScoreBoards(List<ScoreBoard> playerBoards, ScoreBoard dealerBoard) {
		this.playerBoards = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(playerBoards)));
		this.dealerBoard = Objects.requireNonNull(dealerBoard);
	}

	public static ScoreBoards fromAllUsers(List<Player> players, Dealer dealer) {
		Objects.requireNonNull(players);
		Objects.requireNonNull(dealer);

		List<ScoreBoard> playerBoards = players.stream()
			.map(ScoreBoard::of)
			.collect(Collectors.toList());

		ScoreBoard dealerBoard = ScoreBoard.of(dealer);
		return new ScoreBoards(playerBoards, dealerBoard);
	}

	public DealerResult calculateDealerResults() {
		return playerBoards.stream()
			.map(scoreBoard -> scoreBoard.match(dealerBoard))
			.collect(collectingAndThen(toList(), DealerResult::new));
	}

	public PlayerResults calculatePlayersResult() {
		return playerBoards.stream()
			.map(playerBoard -> playerBoard.createPlayerResult(dealerBoard))
			.collect(collectingAndThen(toList(), PlayerResults::new));
	}

	public List<ScoreBoard> getScoreBoards() {
		List<ScoreBoard> result = new ArrayList<>(playerBoards);
		result.add(dealerBoard);
		return Collections.unmodifiableList(result);
	}
}
