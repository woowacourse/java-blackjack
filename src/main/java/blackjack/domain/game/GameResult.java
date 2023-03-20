package blackjack.domain.game;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import blackjack.domain.participant.Player;

public class GameResult {

    private final Map<Player, ResultType> playerResult;

    public GameResult(final Map<Player, ResultType> playerResult) {
        this.playerResult = playerResult;
    }

    public Map<ResultType, Integer> findDealerResult() {
        final Map<ResultType, Integer> dealerResult = new EnumMap<>(ResultType.class);

        for (final Player player : playerResult.keySet()) {
            final ResultType resultType = reverseResult(playerResult.get(player));
            dealerResult.put(resultType, dealerResult.getOrDefault(resultType, 0) + 1);
        }

        return Collections.unmodifiableMap(dealerResult);
    }

    private ResultType reverseResult(final ResultType resultType) {
        if (resultType.equals(ResultType.WIN)) {
            return ResultType.LOSE;
        }
        if (resultType.equals(ResultType.LOSE)) {
            return ResultType.WIN;
        }
        return ResultType.PUSH;
    }
}
