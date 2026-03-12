package dto;

import domain.Dealer;
import domain.GameResult;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public record DealerResultDto(
        ParticipantDto dealerDto,
        int score,
        Map<GameResult, Integer> dealerResult
) {
    public static DealerResultDto from(Dealer dealer, List<PlayerResultDto> playerResults) {
        Map<GameResult, Integer> totalResult = initializeTotalResult();

        playerResults.stream()
                .map(PlayerResultDto::result)
                .map(GameResult::reverse)
                .forEach(result -> totalResult.merge(result, 1, Integer::sum));

        return new DealerResultDto(ParticipantDto.from(dealer), dealer.getOwnCardsSum(), totalResult);
    }

    private static Map<GameResult, Integer> initializeTotalResult() {
        Map<GameResult, Integer> map = new EnumMap<>(GameResult.class);
        for (GameResult result : GameResult.values()) {
            map.put(result, 0);
        }
        return map;
    }
}