package domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.card.Card;
import domain.result.Versus;

public class Players {

	private static final String NOT_BLACK_JACK_SITUATION_ERROR_MESSAGE = "[Error] BlackJack 이 없습니다.";

	private final List<Player> players;

	public Players(List<Name> names, List<List<Card>> initCards) {
		this.players = IntStream.range(0, names.size())
			.mapToObj(i -> new Player(names.get(i), initCards.get(i)))
			.collect(Collectors.toList());
	}

	public void addCardByName(Name name, Card card) {
		players.stream().filter(player -> player.isNameMatch(name)).forEach(player -> player.addCard(card));
	}

	public List<String> showHands() {
		return players.stream().map(Player::showHand).collect(Collectors.toList());
	}

	public String showHandByName(Name name) {
		return players.stream()
			.filter(player -> player.isNameMatch(name))
			.map(Player::showHand).findFirst().orElseThrow();
	}

	public List<String> showHandsAndBestScores() {
		return players.stream().map(Player::showHandAndBestScore).collect(Collectors.toList());
	}

	public boolean isAllBust() {
		long count = players.stream()
			.filter(Player::isBust)
			.count();
		return count == players.size();
	}

	public boolean isBustByName(Name name) {
		return players.stream()
			.filter(player -> player.isNameMatch(name))
			.map((Player::isBust)).findFirst().orElseThrow();
	}

	public boolean isMaxScoreByName(Name name) {
		return players.stream()
			.filter(player -> player.isNameMatch(name))
			.map((Player::isMaxScore)).findFirst().orElseThrow();
	}

	public Map<Name, Versus> getResultAtBlackJack(Participant other) {
		if (!other.blackJack) {
			throw new IllegalStateException(NOT_BLACK_JACK_SITUATION_ERROR_MESSAGE);
		}
		Map<Name, Versus> map = new LinkedHashMap<>();
		players.stream().forEach(player -> map.put(player.name, player.compareAtBlackJack(other)));
		return map;
	}

	public Map<Name, Versus> getResultAtFinal(Participant other) {
		Map<Name, Versus> map = new LinkedHashMap<>();
		players.stream().forEach(player -> map.put(player.name, player.compareAtFinal(other)));
		return map;
	}
}
