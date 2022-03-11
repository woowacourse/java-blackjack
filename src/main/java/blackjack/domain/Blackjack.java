package blackjack.domain;

import java.util.List;

import blackjack.domain.dto.ResultDto;

public class Blackjack {
	private static final int NUMBER_OF_INIT_CARD = 2;

	private final PlayerRepository playerRepository = new PlayerRepository();
	private final Dealer dealer;

	public Blackjack(List<String> playerNames) {
		playerRepository.addAll(playerNames);
		this.dealer = new Dealer();
	}

	public void distributeInitialCards(NumberGenerator numberGenerator) {
		for (int i = 0; i < NUMBER_OF_INIT_CARD; ++i) {
			dealer.addCard(dealer.handOutCard(numberGenerator));

			List<Player> players = playerRepository.findAll();
			players.forEach(player -> player
				.addCard(dealer.handOutCard(numberGenerator)));
			playerRepository.saveAll(players);
		}
	}

	public void distributeAdditionalCardToDealer(NumberGenerator numberGenerator) {
		while (dealer.isHit()) {
			dealer.addCard(dealer.handOutCard(numberGenerator));
		}
	}

	public boolean isDistributeMore() {
		return !playerRepository.isEnd();
	}

	public Player getNextPlayer() {
		Player player = playerRepository.findNextPlayer();
		playerRepository.next();
		return player;
	}

	public Player findPlayer(Player player) {
		return playerRepository.findPlayer(player);
	}

	public void distributeAdditionalCardToPlayer(NumberGenerator numberGenerator, Player player) {
		player.addCard(dealer.handOutCard(numberGenerator));
		playerRepository.save(player);
	}

	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}

	public Dealer getDealer() {
		return dealer;
	}

	public ResultDto result() {
		return ScoreBoard.of(dealer, playerRepository.findAll());
	}
}
