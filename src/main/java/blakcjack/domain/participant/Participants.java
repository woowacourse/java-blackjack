package blakcjack.domain.participant;

import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.name.Names;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;

import java.util.*;
import java.util.stream.Collectors;

import static blakcjack.view.OutputView.DELIMITER;

public class Participants {
	private final List<Participant> participants = new ArrayList<>();

	public Participants(final Dealer dealer, final List<Player> players) {
		participants.add(dealer);
		participants.addAll(players);
	}

	private void addPlayers(final Names names) {
		for (Name name : names.toList()) {
			participants.add(new Player(name));
		}
	}

	public void initializeHandsFrom(final Deck deck) {
		for (Participant participant : participants) {
			participant.drawOneCardFrom(deck);
			participant.drawOneCardFrom(deck);
		}
	}

	public String getConcatenatedPlayerNames() {
		return getPlayers()
				.stream()
				.map(Participant::getName)
				.collect(Collectors.joining(DELIMITER));
	}

	public List<Participant> getParticipants() {
		return Collections.unmodifiableList(participants);
	}

	public List<Participant> getParticipantsInDrawOrder() {
		List<Participant> participantsInDrawOrder = new ArrayList<>(participants);
		Collections.rotate(participantsInDrawOrder, participantsInDrawOrder.size() - 1);
		return Collections.unmodifiableList(participantsInDrawOrder);
	}

	public OutcomeStatistics getOutcomeStatistics() {
		final Dealer dealer = getDealer();
		final List<Player> players = getPlayers();
		final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();

		for (final Player player : players) {
			final Outcome playerOutcome = dealer.judgeOutcomeOf(player);
			playersOutcome.put(player.getName(), playerOutcome);
		}
		return new OutcomeStatistics(playersOutcome);
	}

	private Dealer getDealer() {
		return (Dealer) participants.stream()
				.filter(Participant::isDealer)
				.findFirst()
				.orElseThrow(NoDealerExistException::new);
	}

	private List<Player> getPlayers() {
		return participants.stream()
				.filter(participant -> !participant.isDealer())
				.map(participant -> (Player) participant)
				.collect(Collectors.toList());
	}
}
