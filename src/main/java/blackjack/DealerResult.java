package blackjack;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class DealerResult {
    private EnumMap<ResultStatus, Integer> resultStatusBoard;

    private DealerResult(EnumMap<ResultStatus, Integer> resultStatusBoard) {
        this.resultStatusBoard = resultStatusBoard;
    }

    public static DealerResult of(List<GamePlayerResult> gamePlayerResults) {
        EnumMap<ResultStatus, Integer> resultStatusBoard = initializeStatusBoard();

        for (GamePlayerResult gamePlayerResult : gamePlayerResults) {
            increment(resultStatusBoard, gamePlayerResult.getResultStatus());
        }

        return new DealerResult(resultStatusBoard);
    }

    private static EnumMap<ResultStatus, Integer> initializeStatusBoard() {
        EnumMap<ResultStatus, Integer> resultStatusBoard = new EnumMap(ResultStatus.class);

        for (ResultStatus resultStatus : ResultStatus.values()) {
            resultStatusBoard.put(resultStatus, 0);
        }

        return resultStatusBoard;
    }

    private static void increment(EnumMap<ResultStatus, Integer> resultStatusBoard,
                                  ResultStatus resultStatus) {
        resultStatusBoard.put(resultStatus, resultStatusBoard.get(resultStatus) + 1);
    }

    public int getResultWithResultStatus(ResultStatus resultStatus) {
        return resultStatusBoard.get(resultStatus);
    }
}
