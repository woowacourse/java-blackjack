package blackjack.domain;

import java.util.List;
import java.util.Map;

public class Blackjack {
	private final PlayerRepository playerRepository = new PlayerRepository();
	private final Dealer dealer;

	public Blackjack(List<String> playerNames) {
		playerRepository.addAll(playerNames);
		this.dealer = new Dealer();
	}

	public void distributeInitialCards(NumberGenerator numberGenerator) {
		for (int i = 0; i < 2; ++i) {
			dealer.addCard(dealer.handOutCard(numberGenerator));

			List<Player> players = playerRepository.findAll();
			players.forEach(player -> player
				.addCard(dealer.handOutCard(numberGenerator)));
			playerRepository.saveAll(players);
		}
	}

	public void distributeAdditionalCardDealer(NumberGenerator numberGenerator) {
		while (dealer.isHit()) {
			dealer.addCard(dealer.handOutCard(numberGenerator));
		}
	}

	public boolean isDistributeMore() {
		return !playerRepository.isEnd();
	}

	public Player getPlayer() {
		Player player = playerRepository.getPlayer();
		playerRepository.next();
		return player;
	}

	public Player findPlayer(Player player) {
		return playerRepository.findPlayer(player);
	}

	public void distributeAdditionalCardPlayer(NumberGenerator numberGenerator, Player player) {
		player.addCard(dealer.handOutCard(numberGenerator));
		playerRepository.save(player);
	}

	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}

	public Dealer getDealer() {
		return dealer;
	}

	public Map<Player, Integer> result() {
		return ScoreBoard.calculateResult(dealer, playerRepository.findAll());
	}
}
