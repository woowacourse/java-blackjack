package blakcjack.view;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.Cards;
import blakcjack.domain.outcome.OutcomeStatistics;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Participants;

import java.util.Map;
import java.util.stream.Collectors;

import static blakcjack.domain.participant.Dealer.DEALER_NAME;

public class OutputView {
	public static final String DELIMITER = ", ";

	public static void printInitialHandsOf(final Participants participants) {
		System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.%n", DEALER_NAME, getConcatenatedPlayerNamesFrom(participants));
		printHandSummaryOf(participants);
	}

	private static String getConcatenatedPlayerNamesFrom(final Participants participants) {
		return participants.getPlayers()
				.stream()
				.map(Participant::getName)
				.collect(Collectors.joining(DELIMITER));
	}

	private static void printHandSummaryOf(final Participants participants) {
		for (final Participant participant : participants.getParticipants()) {
			System.out.printf("%s: %s%n", participant.getName(), getInitialHandOf(participant));
		}
		System.out.println();
	}

	private static String getInitialHandOf(final Participant participant) {
		Cards initialHand = participant.getInitialHand();
		return getConcatenatedCardsFrom(initialHand);
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
		Cards hand = participant.getHand();
		return String.format("%s카드: %s", participant.getName(), getConcatenatedCardsFrom(hand));
	}

	private static String getConcatenatedCardsFrom(final Cards cards) {
		return cards.toList()
				.stream()
				.map(Card::getCardInformation)
				.collect(Collectors.joining(DELIMITER));
	}

	public static void printFinalOutcomeSummary(final OutcomeStatistics outcomeStatistics) {
		System.out.println("## 최종 승패");
		Map<Participant, Float> participantsProfit = outcomeStatistics.getParticipantsProfit();
		for (final Participant participant : participantsProfit.keySet()) {
			final float participantProfit = participantsProfit.get(participant);
			System.out.printf("%s: %f%n", participant.getName(), participantProfit);
		}
	}
}
