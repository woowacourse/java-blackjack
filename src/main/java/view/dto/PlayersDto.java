package view.dto;

import java.util.List;

public class PlayersDto {

    private List<UserDto> players;

    public static PlayersDto of(List<UserDto> players) {
        return new PlayersDto(players);
    }

    private PlayersDto(List<UserDto> players) {
        this.players = players;
    }

    public List<UserDto> getPlayers() {
        return players;
    }
}
