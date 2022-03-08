package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Result {
	private final List<Player> winners;
	private final List<Player> losers;

	public Result(List<Player> players, Dealer dealer) {
		winners = players.stream()
			.filter(player -> !dealer.isHigher(player) && !player.isBurst())
			.collect(Collectors.toList());
		losers = players.stream()
			.filter(player -> dealer.isHigher(player) || player.isBurst())
			.collect(Collectors.toList());
	}

	public List<Player> getWinners() {
		return winners;
	}

	public List<Player> getLosers() {
		return losers;
	}
}
