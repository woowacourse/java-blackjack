package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.dto.ResultDto;
import blackjack.domain.strategy.NumberGenerator;

public class Blackjack {
	private static final String DUPLICATED_ERROR = "[ERROR] 이름은 중복될 수 없습니다.";
	private static final String NO_PLAYER_ERROR = "[ERROR] 플레이어는 1명 이상이여야 합니다.";
	private static final int NUMBER_OF_INIT_CARD = 2;

	private final PlayerRepository playerRepository;
	private final Dealer dealer;

	public Blackjack(List<String> playerNames) {
		validateNames(playerNames);
		this.playerRepository = new PlayerRepository(playerNames);
		this.dealer = new Dealer();
	}

	private void validateNames(List<String> playerNames) {
		if (playerNames.size() == 0) {
			throw new IllegalArgumentException(NO_PLAYER_ERROR);
		}

		List<String> duplicatedChecker = playerNames.stream().distinct().collect(Collectors.toList());
		if(duplicatedChecker.size() != playerNames.size()) {
			throw new IllegalArgumentException(DUPLICATED_ERROR);
		}
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

	public boolean distributeAdditionalCardToDealer(NumberGenerator numberGenerator) {
		if (dealer.isHit()) {
			dealer.addCard(dealer.handOutCard(numberGenerator));
			return true;
		}

		return false;
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
		return Record.of(dealer, playerRepository.findAll());
	}
}
