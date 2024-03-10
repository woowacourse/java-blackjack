package blackjack.view;

import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.Result;
import blackjack.domain.result.ResultStatus;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultView {
    private static final EnumMap<ResultStatus, String> resultBoard = initializeResultStatus();

    public static void printResult(final Result result) {
        printDealerResult(result.getDealerResult());
        printGamePlayerResults(result.getGamePlayerResults());
    }

    private static void printGamePlayerResults(final List<GamePlayerResult> gamePlayerResults) {
        gamePlayerResults.forEach(ResultView::printGamePlayerResult);
    }

    private static void printGamePlayerResult(final GamePlayerResult gamePlayerResult) {
        System.out.println(String.format("%s: %s", gamePlayerResult.getName(),
                resultBoard.get(gamePlayerResult.getResultStatus())));
    }

    private static void printDealerResult(final DealerResult dealerResult) {
        System.out.println(formatDealerResult(dealerResult));
    }

    private static String formatDealerResult(final DealerResult dealerResult) {
        return dealerResult.getName() + ": " +
                Arrays.stream(ResultStatus.values())
                      .map(resultStatus -> formatDealerResultStatus(dealerResult, resultStatus))
                      .collect(Collectors.joining(" "));
    }

    private static String formatDealerResultStatus(final DealerResult dealerResult,
                                                   final ResultStatus resultStatus) {
        return dealerResult.getResultWithResultStatus(resultStatus) + resultBoard.get(resultStatus);
    }

    private static EnumMap<ResultStatus, String> initializeResultStatus() {
        final EnumMap<ResultStatus, String> resultStatusBoard = new EnumMap<>(ResultStatus.class);
        resultStatusBoard.put(ResultStatus.WIN, "승");
        resultStatusBoard.put(ResultStatus.DRAW, "무");
        resultStatusBoard.put(ResultStatus.LOSE, "패");
        return resultStatusBoard;
    }
}
