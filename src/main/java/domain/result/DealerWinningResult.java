package domain.result;

import java.util.EnumMap;
import java.util.List;

public class DealerWinningResult {
    private final EnumMap<WinningStatus, Integer> winningStatistic;

    public DealerWinningResult(EnumMap<WinningStatus, Integer> winningStatistic) {
        this.winningStatistic = winningStatistic;
    }

    private static DealerWinningResult from(List<PlayerWinningResult> playerPlayerWinningResults) {
        EnumMap<WinningStatus, Integer> dealerGameResult = new EnumMap<>(WinningStatus.class);
        List<WinningStatus> list = playerPlayerWinningResults.stream()
                .map(PlayerWinningResult::winningStatus)
                .map(WinningStatus::reverseResult)
                .toList();

        for (WinningStatus winningStatus : list) {
            dealerGameResult.put(winningStatus, dealerGameResult.getOrDefault(winningStatus, 0) + 1);
        }
        return new DealerWinningResult(dealerGameResult);
    }
}
