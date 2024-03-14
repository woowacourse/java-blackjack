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
    public static void printResult(Result result) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        printDealerResult(result.getDealerResult());
        printGamePlayerResults(result.getGamePlayerResults());
    }

    private static void printGamePlayerResults(List<GamePlayerResult> gamePlayerResults) {
        gamePlayerResults.forEach(ResultView::printGamePlayerResult);
    }

    private static void printGamePlayerResult(GamePlayerResult gamePlayerResult) {
        System.out.println(String.format("%s: %s", gamePlayerResult.getName(), (int)gamePlayerResult.getProfit().getValue()));
    }

    private static void printDealerResult(DealerResult dealerResult) {
        System.out.println(String.format("%s: %s", dealerResult.getName(), (int)dealerResult.getProfit().getValue()));
    }
}
