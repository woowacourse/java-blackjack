package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.dto.DealerResultDto;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerResultDto;
import blackjack.dto.TableStatusDto;

public class OutputView {

	public static void printInitialStatus(final TableStatusDto dealerStatus, final List<TableStatusDto> playersStatus) {
		printNames(dealerStatus, playersStatus);
		printHand(dealerStatus, playersStatus);
	}

	private static void printNames(final TableStatusDto dealerStatus, final List<TableStatusDto> tableStatuses) {
		final String playerNames = tableStatuses.stream()
			.map(TableStatusDto::getRoleName)
			.collect(Collectors.joining(", "));

		final String message = dealerStatus.getRoleName() + "와 " + playerNames + "에게 2장의 카드를 나누었습니다.";
	}

	private static void printHand(TableStatusDto dealerStatus, List<TableStatusDto> playersStatus) {
		printPersonalHand(dealerStatus);
		playersStatus.forEach(OutputView::printPersonalHand);
	}

	public static void printPersonalHand(final TableStatusDto status) {
		System.out.print(status.getRoleName() + ": ");
		System.out.println(String.join(", ", status.getCards()));
	}

	public static void printDealerStatus(DealerTurnDto dealerTurn) {
		if (dealerTurn.isDraw()) {
			System.out.println(dealerTurn.getName() + "는" + dealerTurn.getStandard() + "이하라 한장의 카드를 더 받았습니다.");
			return;
		}
		System.out.println(dealerTurn.getName() + "는" + dealerTurn.getStandard() + "이상이라 카드를 더 받지 않았습니다.");
	}

	public static void printFinalResult(FinalResultDto finalResult) {
		final DealerResultDto dealerResult = finalResult.getDealerResults();
		final List<PlayerResultDto> playerResult = finalResult.getPlayerResults();
		printDealerFinalResult(dealerResult);
		playerResult.forEach(OutputView::printPlayerFinalResult);
		System.out.println("## 최종 승패");
		printDealerOutcome(dealerResult);
		printPlayerOutcome(playerResult);
	}

	private static void printDealerFinalResult(final DealerResultDto result) {
		System.out.print(result.getName() + " 카드: ");
		System.out.print(String.join(", ", result.getCards()));
		System.out.println(" - 결과: " + result.getTotalScore());
	}

	private static void printPlayerFinalResult(final PlayerResultDto result) {
		System.out.print(result.getName() + "카드: ");
		System.out.print(String.join(", ", result.getCards()));
		System.out.println(" - 결과: " + result.getTotalScore());
	}

	private static void printDealerOutcome(final DealerResultDto dealerResult) {
		System.out.print(dealerResult.getName() + ": ");
		final String dealerOutcome = dealerResult.getCompeteResult().entrySet().stream()
			.map(entry -> "" + entry.getValue() + entry.getKey())
			.collect(Collectors.joining(" "));
		System.out.println(dealerOutcome);
	}

	private static void printPlayerOutcome(final List<PlayerResultDto> playerResults) {
		for (PlayerResultDto playerResult : playerResults) {
			System.out.print(playerResult.getName() + ": ");
			System.out.println(playerResult.getCompeteResult().getValue());
		}
	}
}
