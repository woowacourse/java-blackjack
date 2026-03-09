package domain.gamer.dto;

import domain.gamer.Player;

import java.util.List;

public record PlayersNameDto(
        List<String> playerNames
) {

    public static PlayersNameDto from(List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getMyName)
                .toList();
        return new PlayersNameDto(playerNames);
    }

}
