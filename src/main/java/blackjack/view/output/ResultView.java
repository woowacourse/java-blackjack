package blackjack.view.output;

import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.Result;

import java.util.List;

public class ResultView {
    private ResultView() {
    }

    public static void printResult(final Result result) {
        printDealerResult(result.getDealerResult());
        printGamePlayerResults(result.getGamePlayerResults());
    }

    private static void printGamePlayerResults(final List<GamePlayerResult> gamePlayerResults) {
        gamePlayerResults.forEach(ResultView::printGamePlayerResult);
    }

    private static void printGamePlayerResult(final GamePlayerResult gamePlayerResult) {
        System.out.printf("%s: %d%n", gamePlayerResult.getName(), gamePlayerResult.getPrizeMoney());
    }

    private static void printDealerResult(final DealerResult dealerResult) {
        System.out.println(formatDealerResult(dealerResult));
    }

    private static String formatDealerResult(final DealerResult dealerResult) {
        return String.format("%s: %d", dealerResult.getName(), dealerResult.getPrizeMoney());
    }
}
