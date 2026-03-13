package dto;

import domain.Dealer;
import domain.GameResult;
import domain.Player;

public record PlayerResultDto(
        ParticipantDto playerDto,
        int score,
        GameResult result
) {
    public static PlayerResultDto from(Player player, Dealer dealer) {
        return new PlayerResultDto(
                ParticipantDto.from(player),
                player.getOwnCardsSum(),
                player.calculateGameResult(dealer)
        );
    }
}
