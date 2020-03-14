package domain.gamer;

import domain.card.Deck;
import domain.result.Score;

import java.util.*;
import java.util.stream.Collectors;

public class Gamers {
	private static final int DEALER_INDEX = 0;
	private static final int PLAYER_START_INDEX = 1;

	private final List<Gamer> gamers;

	public Gamers(List<Player> players, Dealer dealer) {
		this.gamers = of(players, dealer);
	}

	private static List<Gamer> of(List<Player> players, Dealer dealer) {
		List<Gamer> gamers = new ArrayList<>();
		gamers.add(dealer);
		gamers.addAll(players);

		return gamers;
	}

	public void giveCardToAll(Deck deck) {
		for (Gamer gamer : gamers) {
			gamer.hit(deck.drawCard());
		}
	}

	public Map<Player, Score> playersScore() {
		Map<Player, Score> playerToScore = new HashMap<>();
		for (Player player : getPlayers()) {
			playerToScore.put(player, player.calculateScore());
		}

		return playerToScore;
	}

	public Score dealerScore() {
		return getDealer().calculateScore();
	}

	public List<Player> getPlayers() {
		return gamers.subList(PLAYER_START_INDEX, gamers.size())
				.stream()
				.map(gamer -> (Player) gamer)
				.collect(Collectors.toUnmodifiableList());
	}

	public Dealer getDealer() {
		return (Dealer) gamers.get(DEALER_INDEX);
	}

	public List<Gamer> getGamers() {
		return Collections.unmodifiableList(gamers);
	}
}
