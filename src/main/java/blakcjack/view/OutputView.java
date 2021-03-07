package blakcjack.view;

import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;
import blakcjack.domain.participant.Players;

import java.util.Map;

import static blakcjack.domain.participant.Dealer.DEALER_NAME;

public class OutputView {
	public static void printInitialHandsOf(final Players players, final Dealer dealer) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(makeCardDistributionMessage(players, dealer));

		stringBuilder.append(makeHandSummaryOf(dealer));
		for (final Player player : players.toList()) {
			stringBuilder.append(makeHandSummaryOf(player));
		}
		System.out.println(stringBuilder.toString());
	}

	public static void printHandOf(final Participant participant) {
		System.out.println(makeHandSummaryOf(participant));
	}

	private static String makeCardDistributionMessage(final Players players, final Dealer dealer) {
		return String.format("%s 와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), players.getConcatenatedNames());
	}

	private static String makeHandSummaryOf(final Participant participant) {
		return participant.getName() + ": " + participant.getInitialHandInformation() + System.lineSeparator();
	}

	public static void printDealerAdditionalCardMessage() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printFinalHandsSummaryOf(final Players players, final Dealer dealer) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(makeFinalSummaryOf(dealer));

		for (Participant player : players.toList()) {
			stringBuilder.append(makeFinalSummaryOf(player));
		}
		System.out.println(stringBuilder.toString());
	}

	private static String makeFinalSummaryOf(final Participant participant) {
		return String.format("%s - 결과: %d%n", makeFinalCardSummaryOf(participant), participant.getScore());
	}

	private static String makeFinalCardSummaryOf(final Participant participant) {
		return participant.getName() + "카드: " + participant.getHandInformation();
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
