package blackjack.dto;

import blackjack.model.GameResult;
import java.util.List;

public record DealerResultDto(int win, int lose, int push) {
    public static DealerResultDto from(List<PlayerResultDto> playerResultDtos) {
        int winCount = (int) playerResultDtos.stream().filter(playerResultDto ->
            playerResultDto.result() == GameResult.DEALER_WIN).count();
        int loseCount = (int) playerResultDtos.stream().filter(playerResultDto ->
            playerResultDto.result() == GameResult.PLAYER_WIN).count();
        int playerCount = playerResultDtos.size();
        int pushCount = playerCount - loseCount - winCount;
        return new DealerResultDto(winCount, loseCount, pushCount);
    }
}
