import java.util.List;
import java.util.stream.Collectors;

public class Blackjack {
	private static final String ERROR_MESSAGE_PLAYER_NUMBER_EXCEED = "[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.";
	private static final int MAX_PLAYER_NUMBER = 8;

	private final Participant dealer;
	private final List<Participant> players;
	private final CardDeck cardDeck;

	private Blackjack(List<Participant> players) {
		this.dealer = Participant.createDealer();
		this.players = players;
		this.cardDeck = new CardDeck();
	}

	public static Blackjack createFrom(List<String> playerNames) {
		validatePlayerNumber(playerNames);
		List<Participant> players = playerNames.stream()
				.map(Participant::createPlayer)
				.collect(Collectors.toList());

		return new Blackjack(players);
	}

	private static void validatePlayerNumber(List<String> players) {
		if (players.size() > MAX_PLAYER_NUMBER) {
			throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_NUMBER_EXCEED);
		}
	}

	public void handOutStartingCards() {
		for (int i = 0; i < 2; i++) {
			handOutCard();
		}
	}

	private void handOutCard() {
		dealer.receiveCard(cardDeck.pickCard());
		for (Participant player : players) {
			player.receiveCard(cardDeck.pickCard());
		}
	}

}
