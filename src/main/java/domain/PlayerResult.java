package domain;

import java.util.HashMap;
import java.util.Map;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;

public class PlayerResult {
	private Map<Name, Double> playerResult = new HashMap<>();

	public PlayerResult(GameParticipant participant) {
		Dealer dealer = participant.getDealer();
		Players players = participant.getPlayers();
		for (Player player : players.getPlayers()) {
			playerResult.put(player.getName(), player.beatDealer(dealer));
		}
	}

	public Map<Name, Double> getResult() {
		return playerResult;
	}
}
