package domain.gamer.dto;

import domain.gamer.Gamer;

public record GamerResultDto(
        GamerHandDto playerHand,
        int resultScore
) {
    public static GamerResultDto from(Gamer gamer) {
        int resultScore = gamer.getResultScore();
        return new GamerResultDto(GamerHandDto.from(gamer), resultScore);
    }
}
