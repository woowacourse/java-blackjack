package blackjack.view;

import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.Result;
import blackjack.domain.result.ResultStatus;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final EnumMap<ResultStatus, String> resultBoard = initializeResultStatus();

    public static void printResult(Result result) {
        printDealerResult(result.getDealerResult());
        printGamePlayerResults(result.getGamePlayerResults());
    }

    private static void printGamePlayerResults(List<GamePlayerResult> gamePlayerResults) {
        gamePlayerResults.forEach(ResultView::printGamePlayerResult);
    }

    private static void printGamePlayerResult(GamePlayerResult gamePlayerResult) {
        System.out.println(String.format("%s: %s", gamePlayerResult.getName(),
                resultBoard.get(gamePlayerResult.getResultStatus())));
    }

    private static void printDealerResult(DealerResult dealerResult) {
        System.out.println(formatDealerResult(dealerResult));
    }

    private static String formatDealerResult(DealerResult dealerResult) {
        return dealerResult.getName() + ": " +
                Arrays.stream(ResultStatus.values())
                      .map(resultStatus -> formatDealerResultStatus(dealerResult, resultStatus))
                      .filter(result -> !result.isEmpty())
                      .collect(Collectors.joining(" "));
    }

    private static String formatDealerResultStatus(DealerResult dealerResult,
                                                   ResultStatus resultStatus) {
        int result = dealerResult.getResultWithResultStatus(resultStatus);
        if (result == 0) {
            return "";
        }
        return result + resultBoard.get(resultStatus);
    }

    private static EnumMap<ResultStatus, String> initializeResultStatus() {
        EnumMap<ResultStatus, String> resultStatusBoard = new EnumMap<>(ResultStatus.class);
        resultStatusBoard.put(ResultStatus.WIN, "승");
        resultStatusBoard.put(ResultStatus.DRAW, "무");
        resultStatusBoard.put(ResultStatus.LOSE, "패");
        return resultStatusBoard;
    }
}
