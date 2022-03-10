package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

	private final List<Player> players;

	public Players(List<String> names, List<List<Card>> initCards) {
		this.players = IntStream.range(0, names.size())
			.mapToObj(i -> new Player(names.get(i), initCards.get(i)))
			.collect(Collectors.toList());
	}

	public List<Integer> getBestScores() {
		return players.stream().map(Player::getBestScore).collect(Collectors.toList());
	}

	public boolean isExistBlackJack() {
		return players.stream().filter(Player::isBlackJack).count() != 0;
	}

	public Map<String, Versus> initCompare(boolean isBlackJack) {
		Map<String, Versus> map = new LinkedHashMap<>();
		players.stream().forEach(player -> map.put(player.name, player.initCompare(isBlackJack)));
		return map;
	}

	public Map<String, Versus> finalCompare(Participant other) {
		Map<String, Versus> map = new LinkedHashMap<>();
		players.stream().forEach(player -> map.put(player.name, player.finalCompare(other)));
		return map;
	}

	public List<String> showHands() {
		return players.stream().map(Player::showHand).collect(Collectors.toList());
	}

	public List<String> showHandsAndBestScores() {
		return players.stream().map(Player::showHandAndBestScore).collect(Collectors.toList());
	}

	public void addCardByName(String name, Card card) {
		players.stream().filter(player -> player.compareName(name)).forEach(player -> player.addCard(card));
	}

	public String showHandByName(String name) {
		return players.stream()
			.filter(player -> player.compareName(name))
			.map(Player::showHand).findFirst().orElseThrow();
	}

	public boolean isBustByName(String name) {
		return players.stream()
			.filter(player -> player.compareName(name))
			.map((Player::isBust)).findFirst().orElseThrow();
	}

	public boolean isBlackJackByName(String name) {
		return players.stream()
			.filter(player -> player.compareName(name))
			.map((Player::isBlackJack)).findFirst().orElseThrow();
	}

	public boolean isAllBust() {
		long count = players.stream()
			.filter(Player::isBust)
			.count();

		return count == players.size();
	}
}
