package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import blackjack.domain.Outcome;
import blackjack.domain.role.Role;

public class OutputView {

	private static final String OUTPUT_CONTEXT_DISTRIBUTOR = ", ";
	private static final String WITH = "와 ";
	private static final String IS = "는";
	private static final String SPACE = " ";
	private static final String EMPTY = "";
	private static final String DISTRIBUTED_TWO_CARDS = "에게 2장의 카드를 나누었습니다.";
	private static final String ROLE_NAME_INFORMATION_DISTRIBUTOR = ": ";
	private static final String RESULT = " - 결과: ";
	private static final String FINAL_OUTCOME = "## 최종 승패";
	private static final String CARD = "카드";
	private static final String RECEIVED_ONE_MORE_CARD = "이하라 한장의 카드를 더 받았습니다.";
	private static final String FAIL_TO_RECEIVE_ONE_MORE_CARD = "이상이라 카드를 더 받지 않았습니다.";

	public static void printInitialStatus(final Role dealerStatus, final List<Role> playersStatus) {
		printNames(dealerStatus, playersStatus);
		printHand(dealerStatus, playersStatus);
	}

	private static void printNames(final Role dealerStatus, final List<Role> tableStatuses) {
		final String playerNames = tableStatuses.stream()
			.map(Role::getName)
			.collect(Collectors.joining(OUTPUT_CONTEXT_DISTRIBUTOR));

		final String message = dealerStatus.getName() + WITH + playerNames + DISTRIBUTED_TWO_CARDS;
		System.out.print("\n");
		System.out.println(message);
	}

	private static void printHand(final Role dealerStatus, List<Role> playersStatus) {
		printPersonalHand(dealerStatus);
		playersStatus.forEach(OutputView::printPersonalHand);
		System.out.print("\n");
	}

	public static void printPersonalHand(final Role roleStatus) {
		System.out.print(roleStatus.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.println(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, roleStatus.getCardsInformation()));
	}

	public static void printDealerStatus(final Role dealer) {
		System.out.print("\n");
		if (dealer.wantDraw()) {
			System.out.println(
				dealer.getName() + IS + SPACE + dealer.getDrawStandard() + RECEIVED_ONE_MORE_CARD + "\n");
			return;
		}
		System.out.println(
			dealer.getName() + IS + SPACE + (dealer.getDrawStandard() + 1) + FAIL_TO_RECEIVE_ONE_MORE_CARD + "\n");
	}

	public static void printFinalResult(final Role dealerResult, final List<Role> playersResult) {
		printDealerFinalResult(dealerResult);
		playersResult.forEach(OutputView::printPlayerFinalResult);
		System.out.print("\n");
		System.out.println(FINAL_OUTCOME);
		printDealerOutcome(dealerResult);
		printPlayerOutcome(playersResult);
	}

	private static void printDealerFinalResult(final Role result) {
		System.out.print(result.getName() + SPACE + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.print(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, result.getCardsInformation()));
		System.out.println(RESULT + result.calculateFinalResult());
	}

	private static void printPlayerFinalResult(final Role result) {
		System.out.print(result.getName() + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.print(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, result.getCardsInformation()));
		System.out.println(RESULT + result.calculateFinalResult());
	}

	private static void printDealerOutcome(final Role dealerResult) {
		System.out.print(dealerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		final String dealerOutcome = dealerResult.getCompeteResult()
			.entrySet()
			.stream()
			.map(entry -> EMPTY + entry.getValue() + entry.getKey().getValue())
			.collect(Collectors.joining(SPACE));
		System.out.println(dealerOutcome);
	}

	private static void printPlayerOutcome(final List<Role> playersResult) {
		for (Role playerResult : playersResult) {
			System.out.print(playerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
			System.out.println(getValue(playerResult.getCompeteResult()));
		}
	}

	private static String getValue(final Map<Outcome, Integer> rawResult) {
		final Outcome outcome = rawResult.keySet()
			.stream()
			.findFirst()
			.orElseThrow(NoSuchElementException::new);
		return outcome.getValue();
	}
}
