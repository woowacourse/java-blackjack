package dto;

import domain.Dealer;
import domain.Player;

public record PlayerResultDto(
        ParticipantDto playerDto,
        int score,
        double playerEarnMoney
) {
    public static PlayerResultDto from(Player player, Dealer dealer) {
        return new PlayerResultDto(
                ParticipantDto.from(player),
                player.getOwnCardsSum(),
                player.calculateEarnMoney(dealer)
        );
    }
}