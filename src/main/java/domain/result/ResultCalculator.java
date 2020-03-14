package domain.result;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import domain.user.Dealer;
import domain.user.Player;

public class ResultCalculator {
	private final List<Player> players;
	private final Dealer dealer;

	public ResultCalculator(List<Player> players, Dealer dealer) {
		this.players = new ArrayList<>(Objects.requireNonNull(players));
		this.dealer = Objects.requireNonNull(dealer);
	}

	public DealerResult calculateDealerResults() {
		return players.stream()
			.map(player -> player.calculateMatchResult(dealer))
			.collect(collectingAndThen(toList(), DealerResult::new));
	}

	public PlayerResults calculatePlayersResult() {
		return players.stream()
			.map(user -> new PlayerResult(user, user.calculateMatchResult(dealer)))
			.collect(collectingAndThen(toList(), PlayerResults::new));
	}
}
