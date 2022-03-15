package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

public class BlackJack {
	private static final String ERROR_MESSAGE_PLAYER_NUMBER_EXCEED = "[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.";
	private static final int MAX_PLAYER_NUMBER = 8;
	private static final int STARTING_CARDS_COUNT = 2;

	private final Dealer dealer;
	private final List<Player> players;
	private final CardDeck cardDeck;

	private BlackJack(Dealer dealer, List<Player> players) {
		this.dealer = dealer;
		this.players = players;
		this.cardDeck = CardDeck.create();
	}

	public static BlackJack from(List<String> playerNames) {
		validatePlayerNumber(playerNames);
		List<Player> players = playerNames.stream()
			.map(Name::from)
			.map(Player::new)
			.collect(Collectors.toList());

		return new BlackJack(new Dealer(), players);
	}

	private static void validatePlayerNumber(List<String> players) {
		if (players.size() > MAX_PLAYER_NUMBER) {
			throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_NUMBER_EXCEED);
		}
	}

	public void handOutStartingCards() {
		for (int i = 0; i < STARTING_CARDS_COUNT; i++) {
			handOutCardToAll();
		}
	}

	private void handOutCardToAll() {
		handOutCardTo(dealer);
		for (Participant player : players) {
			handOutCardTo(player);
		}
	}

	public void handOutCardTo(Participant participant) {
		participant.receiveCard(this.cardDeck.pick());
	}

	public Map<Participant, Boolean> calculateResult() {
		Map<Participant, Boolean> result = new HashMap<>();
		for (Participant player : players) {
			result.put(player, isWin(player));
		}

		return result;
	}

	private boolean isWin(Participant player) {
		if (player.isOverMaxScore()) {
			return false;
		}
		if (dealer.isOverMaxScore()) {
			return true;
		}
		return dealer.getScore() < player.getScore();
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
