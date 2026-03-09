package domain.gamer.dto;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

public record PlayerResultDto(
        PlayerHandDto playerHand,
        int resultScore
) {

    public static PlayerResultDto from(Gamer gamer) {
        int resultScore = gamer.getResultScore();
        return new PlayerResultDto(PlayerHandDto.from(gamer), resultScore);
    }

}
