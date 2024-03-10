package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameResult;
import blackjack.domain.gamer.Players;

public record DealerResultDto(String name, int winCount, int loseCount) {

	public static DealerResultDto fromPlayers(Dealer dealer, Players players) {
		String dealerName = dealer.getName().value();
		List<GameResult> allPlayerGameResults = players.getAllPlayerGameResults(dealer.getScore());
		int dealerWinCount = dealer.calculateWinCount(allPlayerGameResults);
		int dealerLoseCount = dealer.calculateLoseCount(allPlayerGameResults);

		return new DealerResultDto(dealerName, dealerWinCount, dealerLoseCount);
	}
}
