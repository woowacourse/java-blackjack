package blackjack.view;

import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.ResultDto;
import blackjack.dto.TableDto;
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
	private static final String REVENUE_FORMAT = "%.0f";

	public void printInitialStatus(final List<TableDto> tables) {
		printNames(tables);
		printCards(tables);
	}

	private void printNames(final List<TableDto> tables) {
		final String names = tables.stream()
				.map(TableDto::getRoleName)
				.collect(Collectors.joining(OUTPUT_CONTEXT_DISTRIBUTOR));

		System.out.print("\n");
		System.out.println(names + DISTRIBUTED_TWO_CARDS);
	}

	private void printCards(List<TableDto> tables) {
		tables.forEach(this::printPersonalCards);
		System.out.print("\n");
	}

	public void printPersonalCards(final TableDto table) {
		System.out.print(table.getRoleName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.println(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, table.getCards()));
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
		final ResultDto dealerResult = finalResult.getDealerResult();
		final List<ResultDto> playerResult = finalResult.getPlayerResults();
		printDealerFinalResult(dealerResult);
		playerResult.forEach(this::printPlayerFinalResult);
		System.out.print("\n");
		System.out.println(FINAL_OUTCOME);
		printDealerRevenue(dealerResult);
		printPlayerRevenue(playerResult);
	}

	private void printDealerFinalResult(final ResultDto result) {
		System.out.print(result.getName() + SPACE + CARD + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		System.out.print(String.join(OUTPUT_CONTEXT_DISTRIBUTOR, result.getCards()));
		System.out.println(RESULT + printTotalScore(result.getTotalScore(), result.isBust()));
	}

	private void printPlayerFinalResult(final ResultDto result) {
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

	private void printDealerRevenue(final ResultDto dealerResult) {
		System.out.print(dealerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
		printRevenue(dealerResult.getRevenueResult());
	}

	private void printPlayerRevenue(final List<ResultDto> playerResults) {
		for (ResultDto playerResult : playerResults) {
			System.out.print(playerResult.getName() + ROLE_NAME_INFORMATION_DISTRIBUTOR);
			printRevenue(playerResult.getRevenueResult());
		}
	}

	private void printRevenue(final double revenue) {
		System.out.printf(REVENUE_FORMAT + "\n", revenue);
	}
}

	
	