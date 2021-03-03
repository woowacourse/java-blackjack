package blakcjack.View;


import blakcjack.domain.card.Card;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
	public static void printInitialHands(final Dealer dealer, final List<Participant> players) {
		final StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(dealer.getName())
				.append("와 ")
				.append(concatenatePlayerNames(players))
				.append("에게 2장의 카드를 나누었습니다.\n");

		stringBuilder.append(makeCardSummary(dealer));
		for (final Participant participant : players) {
			stringBuilder.append(makeCardSummary(participant));
		}
		System.out.println(stringBuilder.toString());
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
}
