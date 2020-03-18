package view.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.user.PlayersInfo;

public class PlayersDto {

    private List<UserDto> players;

    public static PlayersDto of(PlayersInfo playersInfo) {
        return new PlayersDto(playersInfo);
    }

    private PlayersDto(PlayersInfo playersInfo) {
        this.players = playersInfo.getPlayers()
                .stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    public List<UserDto> getPlayers() {
        return players;
    }
}
