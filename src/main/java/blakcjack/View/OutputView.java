package blakcjack.View;


import blakcjack.domain.Outcome;
import blakcjack.domain.OutcomeStatistics;
import blakcjack.domain.card.Card;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
	public static void printInitialHands(final Dealer dealer, final List<Participant> players) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(makeCardDistributionMessage(dealer, players));

		stringBuilder.append(makeCardSummary(dealer));
		for (final Participant participant : players) {
			stringBuilder.append(makeCardSummary(participant));
		}
		System.out.println(stringBuilder.toString());
	}

	private static String makeCardDistributionMessage(Dealer dealer, List<Participant> players) {
		return String.format("%s 와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), concatenatePlayerNames(players));
	}

	public static void printPlayerHand(final Participant participant) {
		System.out.println(makeCardSummary(participant));
	}

	private static String concatenatePlayerNames(List<Participant> players) {
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

	public static void printFinalHandsSummary(final Dealer dealer, final List<Participant> players) {
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

	public static void printFinalOutcomeSummary(final OutcomeStatistics judgeOutcome, final String dealerName) {
		System.out.println("## 최종 승패");
		printDealerOutcome(judgeOutcome.getDealerOutcome(), dealerName);
		printPlayersOutcome(judgeOutcome.getPlayersOutcome());
	}

	private static void printDealerOutcome(final Map<Outcome, Integer> dealerOutcome, final String dealerName) {
		final StringBuilder stringBuilder = new StringBuilder()
				.append(dealerName)
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
		return " " + count + outcome.toKorean();
	}

	private static void printPlayersOutcome(final Map<String, Outcome> playersOutcome) {
		final StringBuilder stringBuilder = new StringBuilder();
		for (final String name : playersOutcome.keySet()) {
			final Outcome outcome = playersOutcome.get(name);
			stringBuilder.append(name).append(": ").append(outcome.toKorean()).append(System.lineSeparator());
		}
		System.out.println(stringBuilder.toString());
	}
}
