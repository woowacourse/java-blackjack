package dto;

import domain.GameResult;
import domain.Player;
import domain.state.GameState;

public record PlayerResultDto(
        ParticipantDto playerDto,
        int score,
        GameResult result
) {
    public static PlayerResultDto from(Player player, GameState dealerGameState) {
        return new PlayerResultDto(
                ParticipantDto.from(player),
                player.getOwnCardsSum(),
                player.calculateGameResult(dealerGameState)
        );
    }
}
