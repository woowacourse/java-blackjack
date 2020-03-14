package domain.result;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import domain.user.Dealer;
import domain.user.Player;

public class ResultCalculator {
	private final List<Player> players;
	private final Dealer dealer;

	public ResultCalculator(List<Player> players, Dealer dealer) {
		this.players = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(players)));
		this.dealer = Objects.requireNonNull(dealer);
	}

	public DealerResult calculateDealerResults() {
		return players.stream()
			.map(player -> player.calculateMatchResult(dealer))
			.collect(collectingAndThen(toList(), DealerResult::new));
	}

	public PlayerResults calculatePlayersResult() {
		return players.stream()
			.map(player -> new PlayerResult(player, player.calculateMatchResult(dealer)))
			.collect(collectingAndThen(toList(), PlayerResults::new));
	}
}
