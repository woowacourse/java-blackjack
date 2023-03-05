package domain;

import static domain.GameResult.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJack {

	private final Users users;

	private BlackJack(final Users users) {
		this.users = users;
	}

	public static BlackJack of(final Users users) {
		initCards(users);
		return new BlackJack(users);
	}

	private static void initCards(Users users) {
		for (User user : users.getUsers()) {
			hitTwoCards(user);
		}
	}

	private static void hitTwoCards(User user) {
		user.hit(Deck.pickCard());
		user.hit(Deck.pickCard());
	}

	public void giveCard(Player player) {
		users.hitCard(player, Deck.pickCard());
	}

	public void giveCardToDealer() {
		Dealer dealer = users.getDealer();
		dealer.hit(Deck.pickCard());
	}

	public Map<String, GameResult> calculatePlayerResults() {
		List<Player> players = users.getPlayers();
		Dealer dealer = users.getDealer();
		int dealerScore = dealer.getScore();
		return players.stream()
			.collect(Collectors.toMap
				(Player::getName,
					player -> comparePlayerWithDealer(player.getScore(), dealerScore),
					(oldValue, newValue) -> newValue,
					LinkedHashMap::new));
	}

	public Map<GameResult, Integer> calculateDealerResult() {
		Map<GameResult, Integer> dealerResult = new HashMap<>();
		Map<String, GameResult> playerResults = calculatePlayerResults();
		playerResults.values().forEach(result -> convertResult(result, dealerResult));
		return dealerResult;
	}

	private void convertResult(final GameResult playerResult, final Map<GameResult, Integer> dealerResult) {
		Map<GameResult, GameResult> converter = Map.of(
			WIN, LOSE,
			LOSE, WIN,
			PUSH, PUSH
		);
		GameResult convertedResult = converter.get(playerResult);
		dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
	}

	public Card getDealerCard() {
		Dealer dealer = users.getDealer();
		return dealer.getCards().get(0);
	}

	public Map<String, List<Card>> getPlayerToCard() {
		List<Player> players = users.getPlayers();
		return players.stream().collect(Collectors.toMap(
			Player::getName,
			Player::getCards,
			(oldValue, newValue) -> newValue,
			LinkedHashMap::new
		));
	}

	public Map<String, Integer> getPlayerToScore() {
		List<Player> players = users.getPlayers();
		return players.stream().collect(Collectors.toMap(
			Player::getName,
			Player::getScore,
			(oldValue, newValue) -> newValue,
			LinkedHashMap::new
		));
	}

	public List<Player> getHittablePlayers() {
		return users.getHittablePlayers();
	}

	public List<Card> getDealerCards() {
		return users.getDealer().getCards();
	}

	public int getDealerScore() {
		return users.getDealer().getScore();
	}

	public boolean isDealerHittable() {
		return users.isDealerHittable();
	}
}
