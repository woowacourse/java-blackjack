package domain.result;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import domain.user.Dealer;
import domain.user.Player;

public class ScoreBoards {
	private final List<PlayerScoreBoard> playerBoards;
	private final DealerScoreBoard dealerBoard;

	private ScoreBoards(List<PlayerScoreBoard> playerBoards, DealerScoreBoard dealerBoard) {
		this.playerBoards = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(playerBoards)));
		this.dealerBoard = Objects.requireNonNull(dealerBoard);
	}

	public static ScoreBoards fromAllUsers(List<Player> players, Dealer dealer) {
		players = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(players)));
		Objects.requireNonNull(dealer);

		List<PlayerScoreBoard> playerBoards = players.stream()
			.map(PlayerScoreBoard::of)
			.collect(toList());

		DealerScoreBoard dealerBoard = DealerScoreBoard.of(dealer);
		return new ScoreBoards(playerBoards, dealerBoard);
	}

	public UserResults calculateUsersResult() {
		return playerBoards.stream()
			.map(playerBoard -> playerBoard.createPlayerResult(dealerBoard))
			.collect(collectingAndThen(toList(),
				(result) -> UserResults.fromPlayerResultsAndDealer(result, dealerBoard.getUser())));
	}

	public List<ScoreBoard> getScoreBoards() {
		return Stream.concat(playerBoards.stream(), Stream.of(dealerBoard))
			.collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}
}
