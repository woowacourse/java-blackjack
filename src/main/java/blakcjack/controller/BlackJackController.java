package blakcjack.controller;

import blakcjack.domain.card.Deck;
import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;
import blakcjack.domain.name.Names;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Participants;
import blakcjack.domain.participant.Player;
import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;

import java.util.ArrayList;
import java.util.List;

import static blakcjack.view.InputView.*;
import static blakcjack.view.OutputView.*;

public class BlackJackController {
	public void run() {
		final Participants participants = createParticipants();

		playGame(participants);

		OutcomeStatistics outcomeStatistics = participants.getOutcomeStatistics();
		printFinalHandsSummaryOf(participants);
		printFinalOutcomeSummary(outcomeStatistics);
	}

	private Participants createParticipants() {
		final List<Player> players = createPlayers();
		final Dealer dealer = new Dealer();
		return new Participants(dealer, players);
	}

	private List<Player> createPlayers() {
		final Names playerNames = new Names(takePlayerNamesInput());
		final List<Player> players = new ArrayList<>();
		for (Name name : playerNames.toList()) {
			final Money bettingAmount = new Money(takeBettingAmountInput(name));
			players.add(new Player(name, bettingAmount));
		}
		return players;
	}

	private void playGame(final Participants participants) {
		final Deck deck = new Deck(new RandomShuffleStrategy());
		participants.initializeHandsFrom(deck);
		printInitialHandsOf(participants);

		letParticipantsDraw(participants, deck);
	}

	private void letParticipantsDraw(final Participants participants, final Deck deck) {
		for (Participant participant : participants.getParticipantsInDrawOrder()) {
			drawCards(participant, deck);
		}
	}

	private void drawCards(final Participant participant, final Deck deck) {
		if (participant.isDealer()) {
			drawDealerCards((Dealer) participant, deck);
			return;
		}
		drawPlayerCards((Player) participant, deck);
	}

	private void drawDealerCards(final Dealer dealer, final Deck deck) {
		while (dealer.isScoreLowerThanMaximumDrawCriterion()) {
			dealer.drawOneCardFrom(deck);
			printDealerAdditionalCardMessage();
		}
	}

	private void drawPlayerCards(final Player player, final Deck deck) {
		while (isHit(player)) {
			player.drawOneCardFrom(deck);
			printHandOf(player);
		}
	}

	private boolean isHit(final Player player) {
		return player.hasAffordableScoreForHit() && isYes(player);
	}
}
