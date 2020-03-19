package common;

import java.util.List;

public class PlayersDto {
    private List<PlayerDto> playerDtos;

    private PlayersDto(List<PlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }

    public static PlayersDto of(List<PlayerDto> playerDtos) {
        return new PlayersDto(playerDtos);
    }

    public List<PlayerDto> getPlayerDtos() {
        return playerDtos;
    }
}
