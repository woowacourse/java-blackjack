package blakcjack.view;


import blakcjack.domain.card.Card;
import blakcjack.domain.game.BlackjackGame;
import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blakcjack.domain.participant.Dealer.DEALER_NAME;

public class OutputView {
	public static void printInitialHands(final BlackjackGame blackjackGame) {
		final Dealer dealer = blackjackGame.getDealer();
		final List<Player> players = blackjackGame.getPlayers();

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(makeCardDistributionMessage(dealer, players));

		stringBuilder.append(makeCardSummary(dealer));
		for (final Player player : players) {
			stringBuilder.append(makeCardSummary(player));
		}
		System.out.println(stringBuilder.toString());
	}

	private static String makeCardDistributionMessage(Dealer dealer, List<Player> players) {
		return String.format("%s 와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), concatenatePlayerNames(players));
	}

	public static void printPlayerHand(final Participant participant) {
		System.out.println(makeCardSummary(participant));
	}

	private static String concatenatePlayerNames(List<Player> players) {
		return players.stream().map(Participant::getName)
				.collect(Collectors.joining(", "));
	}

	private static String makeCardSummary(final Participant participant) {
		return participant.getName() + ": " + concatenateCardsInformation(getInitialCards(participant)) + System.lineSeparator();
	}

	private static List<Card> getInitialCards(final Participant participant) {
		if (participant instanceof Dealer) {
			final Dealer dealer = (Dealer) participant;
			return dealer.getFirstCard();
		}
		return participant.getCards();
	}

	private static String concatenateCardsInformation(final List<Card> cards) {
		return cards.stream()
				.map(OutputView::getCardInformation)
				.collect(Collectors.joining(", "));
	}

	private static String getCardInformation(final Card card) {
		return card.getCardNumber().getName() + card.getCardSymbol().getName();
	}

	public static void printDealerAdditionalCardMessage() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printFinalHandsSummary(final BlackjackGame blackjackGame) {
		final Dealer dealer = blackjackGame.getDealer();
		final List<Player> players = blackjackGame.getPlayers();

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(makeFinalSummary(dealer));

		for (Participant player : players) {
			stringBuilder.append(makeFinalSummary(player));
		}
		System.out.println(stringBuilder.toString());
	}

	private static String makeFinalSummary(final Participant participant) {
		return String.format("%s - 결과: %d%n", makeFinalCardSummary(participant), participant.calculateScore());
	}

	private static String makeFinalCardSummary(final Participant participant) {
		return participant.getName() + "카드: " + concatenateCardsInformation(participant.getCards());
	}

	public static void printFinalOutcomeSummary(final OutcomeStatistics outcomeStatistics) {
		System.out.println("## 최종 승패");
		printDealerOutcome(outcomeStatistics.getDealerOutcome());
		printPlayersOutcome(outcomeStatistics.getPlayersOutcome());
	}

	private static void printDealerOutcome(final Map<Outcome, Integer> dealerOutcome) {
		final StringBuilder stringBuilder = new StringBuilder()
				.append(DEALER_NAME)
				.append(":");

		for (final Outcome outcome : dealerOutcome.keySet()) {
			final int count = dealerOutcome.get(outcome);
			stringBuilder.append(convertCountToString(count, outcome));
		}

		System.out.println(stringBuilder.toString());
	}

	private static String convertCountToString(final int count, final Outcome outcome) {
		if (count == 0) {
			return "";
		}
		return " " + count + outcome.getMessage();
	}

	private static void printPlayersOutcome(final Map<String, Outcome> playersOutcome) {
		final StringBuilder stringBuilder = new StringBuilder();
		for (final String name : playersOutcome.keySet()) {
			final Outcome outcome = playersOutcome.get(name);
			stringBuilder.append(name).append(": ").append(outcome.getMessage()).append(System.lineSeparator());
		}
		System.out.println(stringBuilder.toString());
	}
}
