package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.BlackJack;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

public class OutputView {

	private static final String OUTPUT_CONTEXT_DISTRIBUTOR = ", ";
	private static final String WITH = "와 ";
	private static final String IS = "는";
	private static final String SPACE = " ";
	private static final String DISTRIBUTED_TWO_CARDS = "에게 2장의 카드를 나누었습니다.";
	private static final String ROLE_NAME_INFORMATION_DISTRIBUTOR = ": ";
	private static final String RESULT = " - 결과: ";
	private static final String FINAL_OUTCOME = "## 최종 수익";
	private static final String CARD = "카드";
	private static final String RECEIVED_ONE_MORE_CARD = "이하라 한장의 카드를 더 받았습니다.";
	private static final String FAIL_TO_RECEIVE_ONE_MORE_CARD = "이상이라 카드를 더 받지 않았습니다.";

	public static void printInitialStatus(final Participant dealerStatus, final List<Participant> playersStatus) {
		printNames(dealerStatus, playersStatus);
		printHand(dealerStatus, playersStatus);
	}

	private static void printNames(final Participant dealerStatus, final List<Participant> tableStatuses) {
		final String playerNames = tableStatuses.stream()
			.map(Participant::getName)
			.collect(Collectors.joining(OUTPUT_CONTEXT_DISTRIBUTOR));

		final String message = dealerStatus.getName() + WITH + playerNames + DISTRIBUTED_TWO_CARDS;
		System.out.print("\n");
		System.out.println(message);
	}

	private static void printHand(final Participant dealerStatus, final List<Participant> playersStatus) {
		printPersonalHand(dealerStatus);
		playersStatus.forEach(OutputView::printPersonalHand);
		System.out.print("\n");
	}

	public static void printPersonalHand(final Participant roleStatus) {
		System.out.print(roleStatus.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.println(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, convertCardsToCardSName(roleStatus.getCards())));
	}

	public static void printDealerStatus(final Participant dealer) {
		System.out.print("\n");
		if (dealer.wantDraw()) {
			System.out.println(
				dealer.getName() + IS + SPACE + dealer.getDrawStandard() + RECEIVED_ONE_MORE_CARD + "\n");
			return;
		}
		System.out.println(
			dealer.getName() + IS + SPACE + (dealer.getDrawStandard() + 1) + FAIL_TO_RECEIVE_ONE_MORE_CARD + "\n");
	}

	public static void printFinalResult(final Participant dealerResult, final List<Participant> playersResult) {
		printDealerFinalResult(dealerResult);
		playersResult.forEach(OutputView::printPlayerFinalResult);
		System.out.print("\n");
		System.out.println(FINAL_OUTCOME);
		printDealerOutcome(dealerResult);
		printPlayerOutcome(playersResult);
	}

	private static void printDealerFinalResult(final Participant result) {
		System.out.print(result.getName() + SPACE + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		printPersonalFinalResult(result);
	}

	private static void printPlayerFinalResult(final Participant result) {
		System.out.print(result.getName() + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		printPersonalFinalResult(result);
	}

	private static void printPersonalFinalResult(final Participant result) {
		System.out.print(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, convertCardsToCardSName(result.getCards())));
		final int score = result.calculateFinalScore();
		String scoreResult = Integer.toString(score);
		if (score > BlackJack.OPTIMIZED_WINNING_NUMBER) {
			scoreResult = BlackJack.BUST_MESSAGE;
		}
		System.out.println(RESULT + scoreResult);
	}

	private static List<String> convertCardsToCardSName(final List<Card> cards) {
		return cards.stream()
			.map(Card::getName)
			.collect(Collectors.toList());
	}

	private static void printDealerOutcome(final Participant dealerResult) {
		System.out.print(dealerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.println(dealerResult.getIncome());
	}

	private static void printPlayerOutcome(final List<Participant> playersResult) {
		for (Participant playerResult : playersResult) {
			System.out.print(playerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
			System.out.println(playerResult.getIncome());
		}
	}

}
