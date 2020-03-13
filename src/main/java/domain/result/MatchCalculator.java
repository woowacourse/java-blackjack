package domain.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.Player;

public class MatchCalculator {
	private final List<Player> players;
	private final Dealer dealer;

	public MatchCalculator(List<Player> players, Dealer dealer) {
		this.players = new ArrayList<>(Objects.requireNonNull(players));
		this.dealer = Objects.requireNonNull(dealer);
	}

	public List<MatchResult> getMatchResults() {
		return players.stream()
			.map(player -> MatchResult.calculatePlayerMatchResult(player, dealer))
			.collect(Collectors.toList());
	}
}
