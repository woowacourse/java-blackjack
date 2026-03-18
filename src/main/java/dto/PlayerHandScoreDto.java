package dto;

import domain.participant.Player;

public record PlayerHandScoreDto(PlayerHandDto playerHandDto, int score) {

    public static PlayerHandScoreDto from(Player player) {
        PlayerHandDto playerHandDto = PlayerHandDto.from(player);
        return new PlayerHandScoreDto(playerHandDto, player.getScore());
    }

    public String getName() {
        return playerHandDto.name();
    }

    public HandDto getHandDto() {
        return playerHandDto.handDto();
    }
}
