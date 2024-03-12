package blackjack.domain.result;

import blackjack.domain.common.Name;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DealerResult {
    private final Name name;
    private final Map<ResultStatus, Count> resultStatusBoard;

    public DealerResult(final Name name, final Map<ResultStatus, Count> resultStatusBoard) {
        this.name = name;
        this.resultStatusBoard = resultStatusBoard;
    }

    public static DealerResult of(final Name name, final List<GamePlayerResult> gamePlayerResults) {
        final Map<ResultStatus, Count> resultStatusBoard = new EnumMap<>(ResultStatus.class);

        for (final GamePlayerResult gamePlayerResult : gamePlayerResults) {
            increment(resultStatusBoard, gamePlayerResult.getResultStatus()
                                                         .reverse());
        }

        return new DealerResult(name, resultStatusBoard);
    }

    private static void increment(final Map<ResultStatus, Count> resultStatusBoard,
                                  final ResultStatus resultStatus) {
        resultStatusBoard.put(resultStatus, resultStatusBoard.getOrDefault(resultStatus, Count.initialValue())
                                                             .increment());
    }

    public String getName() {
        return name.asString();
    }

    public int getCountWithResultStatus(final ResultStatus resultStatus) {
        return resultStatusBoard.getOrDefault(resultStatus, Count.initialValue())
                                .value();
    }
}
