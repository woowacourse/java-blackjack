package blackjack.dto;

import blackjack.model.PlayerBlackjackResult;
import java.util.List;

public record DealerResultDto(int win, int lose, int push) {
    public static DealerResultDto from(List<PlayerResultDto> playerResultDtos) {
        int winCount = (int) playerResultDtos.stream().filter(playerResultDto ->
            playerResultDto.result() == PlayerBlackjackResult.LOSE).count();
        int loseCount = (int) playerResultDtos.stream().filter(playerResultDto ->
            playerResultDto.result() == PlayerBlackjackResult.WIN).count();
        int playerCount = playerResultDtos.size();
        int pushCount = playerCount - loseCount - winCount;
        return new DealerResultDto(winCount, loseCount, pushCount);
    }
}
