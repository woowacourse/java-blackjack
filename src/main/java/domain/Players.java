package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;

public class Players {
	private final List<Player> players = new ArrayList<>();

	public Players(List<Name> names, List<Money> bettingMoneys) {
		for (int i = 0; i < names.size(); i++) {
			this.players.add(new Player(names.get(i), bettingMoneys.get(i)));
		}
	}

	public List<String> getUserNames() {
		return players.stream().map(Participant::getName).collect(Collectors.toList());
	}

	public List<Player> getPlayers() {
		return players;
	}
}
