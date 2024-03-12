package blackjack.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;

public record PlayerResultsDto(Map<String, String> playerNameResults) {

	public static PlayerResultsDto ofPlayersAndDealerScore(Players players, Dealer dealer) {
		Map<String, String> nameResults = new LinkedHashMap<>();

		players.getPlayers().forEach(player -> nameResults.put(
			player.getName().value(),
			player.getGameResult(dealer).getName()
		));

		return new PlayerResultsDto(nameResults);
	}
}
