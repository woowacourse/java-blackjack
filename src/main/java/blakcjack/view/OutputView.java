package blakcjack.view;

import blakcjack.domain.outcome.Outcome;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Participants;

import java.util.Map;

import static blakcjack.domain.participant.Dealer.DEALER_NAME;

public class OutputView {
	public static final String DELIMITER = ", ";
	public static final String EMPTY_STRING = "";

	public static void printInitialHandsOf(final Participants participants) {
		System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.%n", DEALER_NAME, participants.getConcatenatedPlayerNames());
		printHandSummaryOf(participants);
	}

	private static void printHandSummaryOf(final Participants participants) {
		for (final Participant participant : participants.getParticipants()) {
			System.out.printf("%s: %s%n", participant.getName(), participant.getInitialHand());
		}
		System.out.println();
	}

	public static void printHandOf(final Participant participant) {
		System.out.println(makeHandSummaryOf(participant));
	}

	public static void printDealerAdditionalCardMessage() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
	}

	public static void printFinalHandsSummaryOf(final Participants participants) {
		for (Participant participant : participants.getParticipants()) {
			System.out.printf("%s - 결과: %d%n", makeHandSummaryOf(participant), participant.getScore());
		}
		System.out.println();
	}

	private static String makeHandSummaryOf(final Participant participant) {
		return String.format("%s카드: %s", participant.getName(), participant.getHand());
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
