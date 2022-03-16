package blackjack.view;

import blackjack.dto.DealerResultDto;
import blackjack.dto.DealerTableDto;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerResultDto;
import blackjack.dto.PlayerTableDto;
import java.util.List;
import java.util.stream.Collectors;

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
	private static final String BUST_MESSAGE = "\uD83D\uDCA3";

	public void printInitialStatus(final DealerTableDto dealerTable,
								   final List<PlayerTableDto> playersTable) {
		printNames(dealerTable, playersTable);
		printHand(dealerTable, playersTable);
	}

	private void printNames(final DealerTableDto dealerTable, final List<PlayerTableDto> playersTable) {
		final String playerNames = playersTable.stream()
				.map(PlayerTableDto::getRoleName)
				.collect(Collectors.joining(OUTPUT_CONTEXT_DISTRIBUTOR));

		final String message = dealerTable.getRoleName() + WITH + playerNames + DISTRIBUTED_TWO_CARDS;
		System.out.print("\n");
		System.out.println(message);
	}

	private void printHand(DealerTableDto dealerTable, List<PlayerTableDto> playersTable) {
		printDealerHand(dealerTable);
		playersTable.forEach(this::printPlayerHand);
		System.out.print("\n");
	}

	private void printDealerHand(final DealerTableDto dealerTable) {
		System.out.print(dealerTable.getRoleName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.println(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, dealerTable.getCards()));
	}

	public void printPlayerHand(final PlayerTableDto playerTable) {
		System.out.print(playerTable.getRoleName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.println(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, playerTable.getCards()));
	}

	public void printDealerStatus(DealerTurnDto dealerTurn) {
		System.out.print("\n");
		if (dealerTurn.isDraw()) {
			System.out.println(
					dealerTurn.getName() + IS + SPACE + dealerTurn.getStandard() + RECEIVED_ONE_MORE_CARD + "\n");
			return;
		}
		System.out.println(
				dealerTurn.getName() + IS + SPACE + dealerTurn.getStandard() + FAIL_TO_RECEIVE_ONE_MORE_CARD + "\n");
	}

	public void printFinalResult(FinalResultDto finalResult) {
		final DealerResultDto dealerResult = finalResult.getDealerResult();
		final List<PlayerResultDto> playerResult = finalResult.getPlayerResults();
		printDealerFinalResult(dealerResult);
		playerResult.forEach(this::printPlayerFinalResult);
		System.out.print("\n");
		System.out.println(FINAL_OUTCOME);
		printDealerRevenue(dealerResult);
		printPlayerRevenue(playerResult);
	}

	private void printDealerFinalResult(final DealerResultDto result) {
		System.out.print(result.getName() + SPACE + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.print(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, result.getCards()));
		System.out.println(RESULT + printTotalScore(result.getTotalScore(), result.isBust()));
	}

	private void printPlayerFinalResult(final PlayerResultDto result) {
		System.out.print(result.getName() + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.print(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, result.getCards()));
		System.out.println(RESULT + printTotalScore(result.getTotalScore(), result.isBust()));
	}

	private String printTotalScore(int score, boolean bust) {
		if (bust) {
			return BUST_MESSAGE;
		}
		return Integer.toString(score);
	}

	private void printDealerRevenue(final DealerResultDto dealerResult) {
		System.out.print(dealerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.println(dealerResult.getRevenueResult());
	}

	private void printPlayerRevenue(final List<PlayerResultDto> playerResults) {
		for (PlayerResultDto playerResult : playerResults) {
			System.out.print(playerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
			System.out.println(playerResult.getRevenueResult());
		}
	}
}
