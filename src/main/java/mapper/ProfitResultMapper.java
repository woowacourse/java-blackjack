package mapper;

import domain.match.GameResult;
import dto.ProfitResultDto;

public class ProfitResultMapper {

    public static ProfitResultDto toDto(GameResult gameResult) {
        return new ProfitResultDto(gameResult.calculateDealerProfit(), gameResult.calculatePlayersProfit());
    }
}
