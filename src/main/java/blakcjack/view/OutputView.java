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
	public static final String DELIMITER = ", ";
	public static final String EMPTY_STRING = "";

	public static void printInitialHandsOf(final Players players, final Dealer dealer) {
		System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), players.getConcatenatedNames());
		System.out.println(makeHandSummaryOf(dealer));
		for (final Player player : players.toList()) {
			System.out.println(makeHandSummaryOf(player));
		}
		System.out.println();
	}

	public static void printHandOf(final Participant participant) {
		System.out.println(makeHandSummaryOf(participant));
	}

	private static String makeHandSummaryOf(final Participant participant) {
		return String.format("%s: %s", participant.getName(), participant.getInitialHand());
	}

	public static void printDealerAdditionalCardMessage() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
	}

	public static void printFinalHandsSummaryOf(final Players players, final Dealer dealer) {
		System.out.println(makeFinalCardSummaryOf(dealer));
		for (Participant player : players.toList()) {
			System.out.println(makeFinalCardSummaryOf(player));
		}
		System.out.println();
	}

	private static String makeFinalCardSummaryOf(final Participant participant) {
		return String.format("%s 카드: %s - 결과: %d", participant.getName(), participant.getHand(), participant.getScore());
	}

	public static void printFinalOutcomeSummary(final OutcomeStatistics outcomeStatistics) {
		System.out.println("## 최종 승패");
		printDealerOutcome(outcomeStatistics.getDealerOutcome());
		printPlayersOutcome(outcomeStatistics.getPlayersOutcome());
	}

	private static void printDealerOutcome(final Map<Outcome, Integer> dealerOutcome) {
		System.out.printf("%s: %s%n", DEALER_NAME, getDealerTotalOutcomeSummary(dealerOutcome));
	}

	private static String getDealerTotalOutcomeSummary(final Map<Outcome, Integer> dealerOutcome) {
		StringBuilder stringBuilder = new StringBuilder();
		for (final Outcome outcome : dealerOutcome.keySet()) {
			final int count = dealerOutcome.get(outcome);
			stringBuilder.append(aggregateOutcomeCount(outcome, count));
		}
		return stringBuilder.toString();
	}

	private static String aggregateOutcomeCount(final Outcome outcome, final int count) {
		if (count == 0) {
			return EMPTY_STRING;
		}
		return String.format("%d%s", count, outcome.getMessage());
	}

	private static void printPlayersOutcome(final Map<String, Outcome> playersOutcome) {
		for (final String name : playersOutcome.keySet()) {
			final Outcome outcome = playersOutcome.get(name);
			System.out.printf("%s: %s%n", name, outcome.getMessage());
		}
	}
}
