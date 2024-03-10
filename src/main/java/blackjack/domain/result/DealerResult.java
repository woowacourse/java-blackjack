package blackjack.domain.result;

import blackjack.domain.common.Name;
import java.util.EnumMap;
import java.util.List;

public class DealerResult {
    private final Name name;
    private final EnumMap<ResultStatus, Integer> resultStatusBoard;

    private DealerResult(Name name, EnumMap<ResultStatus, Integer> resultStatusBoard) {
        this.name = name;
        this.resultStatusBoard = resultStatusBoard;
    }

    public static DealerResult of(Name name, List<GamePlayerResult> gamePlayerResults) {
        EnumMap<ResultStatus, Integer> resultStatusBoard = new EnumMap<ResultStatus, Integer>(
                ResultStatus.class);

        for (GamePlayerResult gamePlayerResult : gamePlayerResults) {
            increment(resultStatusBoard, gamePlayerResult.getResultStatus()
                                                         .reverse());
        }

        return new DealerResult(name, resultStatusBoard);
    }

    private static void increment(EnumMap<ResultStatus, Integer> resultStatusBoard,
                                  ResultStatus resultStatus) {
        resultStatusBoard.put(resultStatus, resultStatusBoard.getOrDefault(resultStatus, 0) + 1);
    }

    public String getName() {
        return name.asString();
    }

    public int getResultWithResultStatus(ResultStatus resultStatus) {
        return resultStatusBoard.getOrDefault(resultStatus, 0);
    }
}
