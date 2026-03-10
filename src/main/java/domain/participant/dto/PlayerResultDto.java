package domain.participant.dto;

import domain.participant.Dealer;
import domain.participant.Player;

public record PlayerResultDto(PlayerHandDto playerHand, int resultScore) {

    public static PlayerResultDto from(Player player) {
        int resultScore = player.getResultScore();
        return new PlayerResultDto(PlayerHandDto.of(player), resultScore);
    }

    public static PlayerResultDto from(Dealer dealer) {
        int resultScore = dealer.getResultScore();
        return new PlayerResultDto(PlayerHandDto.generateAllCard(dealer), resultScore);
    }

}
