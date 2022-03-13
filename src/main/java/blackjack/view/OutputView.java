package blackjack.view;

import blackjack.domain.Outcome;
import blackjack.service.BlackJackService;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.dto.DealerResultDto;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerResultDto;
import blackjack.dto.TableStatusDto;

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
	private static final String BUST_MESSAGE = "파산";
	private static final String VICTORY = "승";
	private static final String DEFEAT = "패";
	private static final String TIE = "무";

	public static void printInitialStatus(final TableStatusDto dealerStatus, final List<TableStatusDto> playersStatus) {
		printNames(dealerStatus, playersStatus);
		printHand(dealerStatus, playersStatus);
	}

	private static void printNames(final TableStatusDto dealerStatus, final List<TableStatusDto> tableStatuses) {
		final String playerNames = tableStatuses.stream()
				.map(TableStatusDto::getRoleName)
				.collect(Collectors.joining(OUTPUT_CONTEXT_DISTRIBUTOR));

		final String message = dealerStatus.getRoleName() + WITH + playerNames + DISTRIBUTED_TWO_CARDS;
		System.out.print("\n");
		System.out.println(message);
	}

	private static void printHand(TableStatusDto dealerStatus, List<TableStatusDto> playersStatus) {
		printPersonalHand(dealerStatus);
		playersStatus.forEach(OutputView::printPersonalHand);
		System.out.print("\n");
	}

	public static void printPersonalHand(final TableStatusDto status) {
		System.out.print(status.getRoleName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.println(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, status.getCards()));
	}

	public static void printDealerStatus(DealerTurnDto dealerTurn) {
		System.out.print("\n");
		if (dealerTurn.isDraw()) {
			System.out.println(
					dealerTurn.getName() + IS + SPACE + dealerTurn.getStandard() + RECEIVED_ONE_MORE_CARD + "\n");
			return;
		}
		System.out.println(
				dealerTurn.getName() + IS + SPACE + dealerTurn.getStandard() + FAIL_TO_RECEIVE_ONE_MORE_CARD + "\n");
	}

	public static void printFinalResult(FinalResultDto finalResult) {
		final DealerResultDto dealerResult = finalResult.getDealerResult();
		final List<PlayerResultDto> playerResult = finalResult.getPlayerResults();
		printDealerFinalResult(dealerResult);
		playerResult.forEach(OutputView::printPlayerFinalResult);
		System.out.print("\n");
		System.out.println(FINAL_OUTCOME);
		printDealerOutcome(dealerResult);
		printPlayerOutcome(playerResult);
	}

	private static void printDealerFinalResult(final DealerResultDto result) {
		System.out.print(result.getName() + SPACE + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.print(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, result.getCards()));
		System.out.println(RESULT + printTotalScore(result.getTotalScore(), result.isBust()));
	}

	private static void printPlayerFinalResult(final PlayerResultDto result) {
		System.out.print(result.getName() + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.print(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, result.getCards()));
		System.out.println(RESULT + printTotalScore(result.getTotalScore(), result.isBust()));
	}

	private static String printTotalScore(int score, boolean bust) {
		if (bust) {
			return BUST_MESSAGE;
		}
		return Integer.toString(score);
	}

	private static void printDealerOutcome(final DealerResultDto dealerResult) {
		System.out.print(dealerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		final String dealerOutcome = dealerResult.getCompeteResult().entrySet().stream()
				.map(entry -> EMPTY + entry.getValue() + printOutcome(entry.getKey()))
				.collect(Collectors.joining(SPACE));
		System.out.println(dealerOutcome);
	}

	private static void printPlayerOutcome(final List<PlayerResultDto> playerResults) {
		for (PlayerResultDto playerResult : playerResults) {
			System.out.print(playerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
			System.out.println(printOutcome(playerResult.getCompeteResult()));
		}
	}

	private static String printOutcome(Outcome outcome) {
		if (outcome == Outcome.VICTORY) {
			return VICTORY;
		}
		if (outcome == Outcome.DEFEAT) {
			return DEFEAT;
		}
		return TIE;
	}
}
