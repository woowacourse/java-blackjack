package blakcjack.domain;

import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

import java.util.*;

public class BlackjackGame {
	public static final String DUPLICATE_NAME_ERROR = "중복된 이름이 입력 되었습니다.";
	public static final int INITIAL_VALUE = 0;
	private final Deck deck;
	private final Dealer dealer;
	private final List<Player> players = new ArrayList<>();

	public BlackjackGame(final Deck deck, final List<String> names) {
		this.deck = deck;
		this.dealer = new Dealer();

		validateDuplication(names);

		for (String name : names) {
			players.add(new Player(new Name(name)));
		}
	}

	private void validateDuplication(final List<String> names) {
		Set<String> nameGroup = new HashSet<>(names);
		if (nameGroup.size() != names.size()) {
			throw new IllegalArgumentException(DUPLICATE_NAME_ERROR);
		}
	}

	public void distributeOneCard(final Participant participant) {
		participant.receiveCard(deck.drawCard());
	}

	public void initializeHands() {
		for (Player player : players) {
			distributeOneCard(player);
			distributeOneCard(player);
		}
		distributeOneCard(dealer);
		distributeOneCard(dealer);
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public Dealer getDealer() {
		return dealer;
	}

	public Map<String, Outcome> getPlayersOutcome() {
		final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();

		for (final Player player : players) {
			final Outcome playerOutcome = Outcome.of(player, dealer);
			playersOutcome.put(player.getName(), playerOutcome);
		}
		return playersOutcome;
	}
}
