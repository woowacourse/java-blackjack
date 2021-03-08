package blakcjack.domain.participant;

import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.name.Names;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;

import java.util.*;
import java.util.stream.Collectors;

import static blakcjack.view.OutputView.DELIMITER;

public class Players {
	private final List<Player> players = new ArrayList<>();

	public Players(Names names) {
		addPlayers(names);
	}

	public void initializeHandsFrom(final Deck deck) {
		for (Player player : players) {
			player.drawOneCardFrom(deck);
			player.drawOneCardFrom(deck);
		}
	}

	private void addPlayers(final Names names) {
		for (Name name : names.toList()) {
			players.add(new Player(name));
		}
	}

	public List<Player> toList() {
		return Collections.unmodifiableList(players);
	}

	public OutcomeStatistics getOutcomeStatisticsBy(final Dealer dealer) {
		final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();

		for (final Player player : players) {
			final Outcome playerOutcome = dealer.judgeOutcomeOf(player);
			playersOutcome.put(player.getName(), playerOutcome);
		}
		return new OutcomeStatistics(playersOutcome);
	}

	public String getConcatenatedNames() {
		return players.stream()
				.map(Participant::getName)
				.collect(Collectors.joining(DELIMITER));
	}
}
