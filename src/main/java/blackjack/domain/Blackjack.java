package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.dto.ResultDto;
import blackjack.domain.strategy.NumberGenerator;

public class Blackjack {
	private static final String DUPLICATED_ERROR = "[ERROR] 이름은 중복될 수 없습니다.";
	private static final String NO_PLAYER_ERROR = "[ERROR] 플레이어는 1명 이상이여야 합니다.";
	private static final int NUMBER_OF_INIT_CARD = 2;

	private final Players players;
	private final Dealer dealer;

	public Blackjack(List<String> playerNames) {
		validateNames(playerNames);
		this.players = new Players(playerNames);
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
			players.addCards(dealer, numberGenerator);
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
		return !players.isEnd();
	}

	public Player getNextPlayer() {
		Player player = players.findNextPlayer();
		players.next();
		return player;
	}

	public Player findPlayer(Player player) {
		return players.findPlayer(player);
	}

	public void distributeAdditionalCardToPlayer(NumberGenerator numberGenerator, Player player) {
		players.addCard(dealer, player, numberGenerator);
	}

	public List<Player> getPlayers() {
		return players.getPlayers();
	}

	public Dealer getDealer() {
		return dealer;
	}

	public ResultDto result() {
		return Records.of(dealer, players.getPlayers());
	}
}
