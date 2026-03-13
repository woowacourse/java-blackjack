package dto;

import domain.participant.Player;

public record PlayerHandScoreDto(String name, HandDto hand, int score) {

    public static PlayerHandScoreDto from(Player player) {
        return new PlayerHandScoreDto(player.getName(), HandDto.from(player.getHand()), player.getScore());
    }
}
