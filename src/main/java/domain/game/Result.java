package domain.game;

import java.util.*;

public class Result {
    private final Map<String, ResultInfo> playersResult;
    private final Map<ResultInfo, Integer> dealerResult;

    public Result(Map<String, ResultInfo> playersResult) {
        this.playersResult = playersResult;
        this.dealerResult = setDealerResult(playersResult);
    }

    public Map<String, ResultInfo> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<ResultInfo, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    private Map<ResultInfo, Integer> setDealerResult(Map<String, ResultInfo> playersResult) {
        Map<ResultInfo, Integer> dealerResult = new EnumMap<>(ResultInfo.class);
        Arrays.stream(ResultInfo.values()).forEach(resultInfo -> dealerResult.put(resultInfo, 0));
        for (String name : playersResult.keySet()) {
            ResultInfo playerOutcome = playersResult.get(name);
            ResultInfo dealerOutcome = calculateDealerOutcome(playerOutcome);
            dealerResult.merge(dealerOutcome, 1, Integer::sum);
        }

        return dealerResult;
    }

    private ResultInfo calculateDealerOutcome(ResultInfo playerResult) {
        if (playerResult == ResultInfo.WIN) return ResultInfo.DEFEAT;
        if (playerResult == ResultInfo.DEFEAT) return ResultInfo.WIN;
        return ResultInfo.DRAW;
    }

}
