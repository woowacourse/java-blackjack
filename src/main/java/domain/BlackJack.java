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
			.collect(
				Collectors.toMap(Player::getName, player -> comparePlayerWithDealer(player.getScore(), dealerScore),
					(oldValue, newValue) -> newValue, LinkedHashMap::new));
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

	public String getDealerCardHidden() {
		Card dealerCard = users.getDealer().getCards().get(0);
		return dealerCard.getCardName();
	}

	public Map<String, List<String>> getPlayerToCard() {
		Map<String, List<String>> playerWithCards = new HashMap<>();
		for (Player player : users.getPlayers()) {
			playerWithCards.put(player.getName(), player.getCardNames());
		}

		return playerWithCards;
	}

	public Map<String, Integer> getPlayerToScore() {
		Map<String, Integer> playerWithScore = new LinkedHashMap<>();
		for (Player player : users.getPlayers()) {
			playerWithScore.put(player.getName(), player.getScore());
		}

		return playerWithScore;
	}

	public List<Player> getPlayers(){
		return users.getPlayers();
	}

	public List<String> getDealerCards() {
		return users.getDealer().getCardNames();
	}

	public int getDealerScore() {
		return users.getDealer().getScore();
	}

	public boolean isDealerHittable() {
		return users.isDealerHittable();
	}
}
