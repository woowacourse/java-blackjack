package domain.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.User;

public class MatchCalculator {
	private final List<User> users;
	private final Dealer dealer;

	public MatchCalculator(List<User> users, Dealer dealer) {
		this.users = new ArrayList<>(Objects.requireNonNull(users));
		this.dealer = Objects.requireNonNull(dealer);
	}

	public List<MatchResult> getMatchResults() {
		return users.stream()
			.map(player -> MatchResult.calculatePlayerMatchResult(player, dealer))
			.collect(Collectors.toList());
	}
}
