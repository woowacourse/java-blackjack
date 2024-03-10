package blackjack.domain.result;

import blackjack.domain.common.Name;

import java.util.EnumMap;
import java.util.List;

public class DealerResult {
    private final Name name;
    private final EnumMap<ResultStatus, Integer> resultStatusBoard;

    public DealerResult(final Name name, final EnumMap<ResultStatus, Integer> resultStatusBoard) {
        this.name = name;
        this.resultStatusBoard = resultStatusBoard;
    }

    public static DealerResult of(final Name name, final List<GamePlayerResult> gamePlayerResults) {
        final EnumMap<ResultStatus, Integer> resultStatusBoard = new EnumMap<ResultStatus, Integer>(
                ResultStatus.class);

        for (final GamePlayerResult gamePlayerResult : gamePlayerResults) {
            increment(resultStatusBoard, gamePlayerResult.getResultStatus()
                                                         .reverse());
        }

        return new DealerResult(name, resultStatusBoard);
    }

    private static void increment(final EnumMap<ResultStatus, Integer> resultStatusBoard,
                                  final ResultStatus resultStatus) {
        resultStatusBoard.put(resultStatus, resultStatusBoard.getOrDefault(resultStatus, 0) + 1);
    }

    public String getName() {
        return name.asString();
    }

    public int getResultWithResultStatus(final ResultStatus resultStatus) {
        return resultStatusBoard.get(resultStatus);
    }
}
