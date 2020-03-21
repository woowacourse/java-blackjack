package domain;

import java.util.HashMap;
import java.util.Map;

import domain.participant.Dealer;
import domain.participant.Player;

public class PlayerResult {
	private Map<Player, Double> playerResult = new HashMap<>();

	public PlayerResult(GameParticipant participant) {
		Dealer dealer = participant.getDealer();
		Players players = participant.getPlayers();
		for (Player player : players.getPlayers()) {
			playerResult.put(player, player.computeProfit(dealer));
		}
	}

	public Map<Player, Double> getResult() {
		return playerResult;
	}
}
