package dto;

import java.util.List;
import java.util.stream.Collectors;

import betting.BettingMap;
import betting.Reward;
import participants.Player;

public class BettingResultsDto {
    private final List<BettingResultDto> bettingResults;

    private BettingResultsDto(List<BettingResultDto> bettingResults) {
        this.bettingResults = bettingResults;
    }

    public static BettingResultsDto from(BettingMap bettingMap) {
        return new BettingResultsDto(getResults(bettingMap));
    }

    private static List<BettingResultDto> getResults(BettingMap bettingMap) {
        return bettingMap.getBettingMap()
                .keySet()
                .stream()
                .map(player -> getBettingResultDto(bettingMap, player))
                .collect(Collectors.toUnmodifiableList());

    }

    private static BettingResultDto getBettingResultDto(BettingMap bettingMap, Player player) {
        return BettingResultDto.from(player,
                Reward.from(player.getResult(), bettingMap.getBettingAmountByPlayer(player)));
    }

    public List<BettingResultDto> getBettingResults() {
        return bettingResults;
    }
}
