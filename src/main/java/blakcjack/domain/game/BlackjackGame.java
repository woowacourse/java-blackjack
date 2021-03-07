package blakcjack.domain.game;

import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.name.Names;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;

import java.util.*;

public class BlackjackGame {
	private final Deck deck;
	private final Dealer dealer;
	private final List<Player> players = new ArrayList<>();

	public BlackjackGame(final Deck deck, final Names names) {
		this.deck = deck;
		this.dealer = new Dealer();
		addPlayers(names);
	}

	private void addPlayers(final Names names) {
		for (Name name : names.toList()) {
			players.add(new Player(name));
		}
	}

	public void initializeHands() {
		for (Player player : players) {
			player.drawOneCardFrom(deck);
			player.drawOneCardFrom(deck);
		}
		dealer.drawOneCardFrom(deck);
		dealer.drawOneCardFrom(deck);
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
