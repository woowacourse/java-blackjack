package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.Participant;
import blackjack.domain.card.CardDeck;

public class BlackJack {
	private static final String ERROR_MESSAGE_PLAYER_NUMBER_EXCEED = "[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.";
	private static final int MAX_PLAYER_NUMBER = 8;
	private static final int DEALER_ADDITIONAL_CARD_STANDARD = 16;
	private static final int STARTING_CARDS_COUNT = 2;

	private final Participant dealer;
	private final List<Participant> players;

	private BlackJack(Participant dealer, List<Participant> players) {
		this.dealer = dealer;
		this.players = players;
	}

	public static BlackJack from(List<String> playerNames) {
		validatePlayerNumber(playerNames);
		List<Participant> players = playerNames.stream()
			.map(Participant::createPlayer)
			.collect(Collectors.toList());

		return new BlackJack(Participant.createDealer(), players);
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
		participant.receiveCard(CardDeck.pick());
	}

	public boolean isDealerEnough() {
		return dealer.getScore() > DEALER_ADDITIONAL_CARD_STANDARD;
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

	public Participant getDealer() {
		return dealer;
	}

	public List<Participant> getPlayers() {
		return players;
	}
}
