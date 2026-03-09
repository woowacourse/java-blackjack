package domain.game;

import java.util.*;

public class Result {
    private final Map<String, ResultInfo> playersResult;
    private final Map<ResultInfo, Integer> dealerResult;

    public Result() {
        this.playersResult = new HashMap<>();
        this.dealerResult = new EnumMap<>(ResultInfo.class);
        Arrays.stream(ResultInfo.values()).forEach(resultInfo -> dealerResult.put(resultInfo, 0));
    }

    public Map<String, ResultInfo> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public void setPlayerResult(String name, ResultInfo info) {
        playersResult.put(name, info);
    }

    public Map<ResultInfo, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    public void setDealerResult(Map<String, ResultInfo> playersResult) {
        for (String name : playersResult.keySet()) {
            ResultInfo playerOutcome = playersResult.get(name);
            ResultInfo dealerOutcome = calculateDealerOutcome(playerOutcome);
            dealerResult.merge(dealerOutcome, 1, Integer::sum);
        }
    }

    private ResultInfo calculateDealerOutcome(ResultInfo playerResult) {
        if (playerResult == ResultInfo.WIN) return ResultInfo.DEFEAT;
        if (playerResult == ResultInfo.DEFEAT) return ResultInfo.WIN;
        return ResultInfo.DRAW;
    }
}
