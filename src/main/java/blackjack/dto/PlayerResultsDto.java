package blackjack.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.gamer.Players;

public record PlayerResultsDto(Map<String, String> playerNameResults) {

	public static PlayerResultsDto ofPlayersAndDealerScore(Players players, int dealerScore) {
		Map<String, String> nameResults = new LinkedHashMap<>();

		players.getPlayers().forEach(player -> nameResults.put(
			player.getName().value(),
			player.getGameResult(dealerScore).getName()
		));

		return new PlayerResultsDto(nameResults);
	}
}
