package dto;

import domain.Dealer;
import domain.GameResult;
import domain.Player;

public record PlayerResultDto(
        ParticipantDto playerDto,
        int score,
        double playerEarnMoney
) {
    public static PlayerResultDto from(Player player, Dealer dealer) {
        GameResult gameResult = player.calculateGameResult(dealer);
        double earnMoney = gameResult.getAllocation() * player.getBetAmount();
        return new PlayerResultDto(
                ParticipantDto.from(player),
                player.getOwnCardsSum(),
                earnMoney
        );
    }
}