package blackjack.domain.process;

import blackjack.domain.betting.Profit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingResult {
	private static final int NEGATE = -1;

	private List<Player> players;
	private Dealer dealer;

	public BettingResult(Players players, Dealer dealer) {
		this.players = players.getPlayers();
		this.dealer = dealer;
	}

	private List<Profit> generateProfits() {
		return this.players.stream()
			.map(player -> Profit.of(Match.of(player, this.dealer), player.getBettingToken().getMoney()))
			.collect(Collectors.toList());
	}

	public Map<Gamer, Profit> calculatePlayersBettingResult() {
		Map<Gamer, Profit> bettingResult = new LinkedHashMap<>();
		List<Profit> profits = generateProfits();
		bettingResult.put(this.dealer, Profit.of(Match.NONE, NEGATE * profits.stream()
			.mapToInt(Profit::calculateResult)
			.sum()));
		for (int i = 0; i < this.players.size(); i++) {
			bettingResult.put(this.players.get(i), profits.get(i));
		}
		return bettingResult;
	}
}
