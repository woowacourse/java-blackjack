package blakcjack.domain.game;

import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;

import java.util.*;

import static blakcjack.domain.game.DuplicatePlayerNamesException.DUPLICATE_NAME_ERROR;

public class BlackjackGame {
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
		final Set<String> nameGroup = new HashSet<>(names);
		if (nameGroup.size() != names.size()) {
			throw new DuplicatePlayerNamesException(DUPLICATE_NAME_ERROR);
		}
	}

	public void initializeHands() {
		for (Player player : players) {
			player.drawOneCardFromDeck(deck);
			player.drawOneCardFromDeck(deck);
		}
		dealer.drawOneCardFromDeck(deck);
		dealer.drawOneCardFromDeck(deck);
	}

	public Deck getDeck() {
		return deck;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public Dealer getDealer() {
		return dealer;
	}

	public OutcomeStatistics getOutcomeStatistics() {
		final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();

		for (final Player player : players) {
			final Outcome playerOutcome = dealer.judgeOutcomeOf(player);
			playersOutcome.put(player.getName(), playerOutcome);
		}
		return new OutcomeStatistics(playersOutcome);
	}
}
