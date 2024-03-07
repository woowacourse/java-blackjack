package blackjack.domain.result;

import blackjack.domain.common.Name;
import java.util.EnumMap;
import java.util.List;

public class DealerResult {
    private Name name;
    private EnumMap<ResultStatus, Integer> resultStatusBoard;

    private DealerResult(Name name, EnumMap<ResultStatus, Integer> resultStatusBoard) {
        this.name = name;
        this.resultStatusBoard = resultStatusBoard;
    }

    public static DealerResult of(Name name, List<GamePlayerResult> gamePlayerResults) {
        EnumMap<ResultStatus, Integer> resultStatusBoard = initializeStatusBoard();

        for (GamePlayerResult gamePlayerResult : gamePlayerResults) {
            increment(resultStatusBoard, gamePlayerResult.getResultStatus()
                                                         .reverse());
        }

        return new DealerResult(name, resultStatusBoard);
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

    public String getName() {
        return name.asString();
    }

    public int getResultWithResultStatus(ResultStatus resultStatus) {
        return resultStatusBoard.get(resultStatus);
    }
}
